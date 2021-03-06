package com.folioreader.ui.folio.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bossturban.webviewmarker.TextSelectionSupport;
import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.FolioReader;
import com.folioreader.R;
import com.folioreader.model.HighLight;
import com.folioreader.model.HighlightImpl;
import com.folioreader.model.ReadPosition;
import com.folioreader.model.ReadPositionImpl;
import com.folioreader.model.event.MediaOverlayHighlightStyleEvent;
import com.folioreader.model.event.MediaOverlayPlayPauseEvent;
import com.folioreader.model.event.MediaOverlaySpeedEvent;
import com.folioreader.model.event.ReloadDataEvent;
import com.folioreader.model.event.RewindIndexEvent;
import com.folioreader.model.event.UpdateHighlightEvent;
import com.folioreader.model.quickaction.ActionItem;
import com.folioreader.model.quickaction.QuickAction;
import com.folioreader.model.search.SearchItem;
import com.folioreader.model.sqlite.HighLightTable;
import com.folioreader.ui.base.HtmlTask;
import com.folioreader.ui.base.HtmlTaskCallback;
import com.folioreader.ui.base.HtmlUtil;
import com.folioreader.ui.folio.activity.FolioActivityCallback;
import com.folioreader.ui.folio.mediaoverlay.MediaController;
import com.folioreader.ui.folio.mediaoverlay.MediaControllerCallbacks;
import com.folioreader.util.AppUtil;
import com.folioreader.util.HighlightUtil;
import com.folioreader.util.SMILParser;
import com.folioreader.util.UiUtil;
import com.folioreader.view.FolioWebView;
import com.folioreader.view.LoadingView;
import com.folioreader.view.VerticalSeekbar;
import com.folioreader.view.WebViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.readium.r2_streamer.model.publication.link.Link;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mahavir on 4/2/16.
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class FolioPageFragment
        extends Fragment
        implements HtmlTaskCallback, MediaControllerCallbacks, FolioWebView.SeekBarListener {

    public static final String LOG_TAG = FolioPageFragment.class.getSimpleName();
    public static final String KEY_FRAGMENT_FOLIO_POSITION = "com.folioreader.ui.folio.fragment.FolioPageFragment.POSITION";
    public static final String KEY_FRAGMENT_FOLIO_BOOK_TITLE = "com.folioreader.ui.folio.fragment.FolioPageFragment.BOOK_TITLE";
    public static final String KEY_FRAGMENT_EPUB_FILE_NAME = "com.folioreader.ui.folio.fragment.FolioPageFragment.EPUB_FILE_NAME";
    private static final String KEY_IS_SMIL_AVAILABLE = "com.folioreader.ui.folio.fragment.FolioPageFragment.IS_SMIL_AVAILABLE";
    private static final String BUNDLE_READ_POSITION_CONFIG_CHANGE = "BUNDLE_READ_POSITION_CONFIG_CHANGE";
    public static final String BUNDLE_SEARCH_ITEM = "BUNDLE_SEARCH_ITEM";

    private static final int ACTION_ID_COPY = 1001;
    private static final int ACTION_ID_SHARE = 1002;
    private static final int ACTION_ID_HIGHLIGHT = 1003;
    private static final int ACTION_ID_DEFINE = 1004;

    private static final int ACTION_ID_HIGHLIGHT_COLOR = 1005;
    private static final int ACTION_ID_DELETE = 1006;

    private static final int ACTION_ID_HIGHLIGHT_YELLOW = 1007;
    private static final int ACTION_ID_HIGHLIGHT_GREEN = 1008;
    private static final int ACTION_ID_HIGHLIGHT_BLUE = 1009;
    private static final int ACTION_ID_HIGHLIGHT_PINK = 1010;
    private static final int ACTION_ID_HIGHLIGHT_UNDERLINE = 1011;
    private static final String KEY_TEXT_ELEMENTS = "text_elements";
    private static final String SPINE_ITEM = "spine_item";

    private String mHtmlString = null;
    private boolean hasMediaOverlay = false;
    private String mAnchorId;
    private String rangy = "";
    private String highlightId;

    private ReadPosition lastReadPosition;
    private Bundle outState;
    private Bundle savedInstanceState;

    private View mRootView;

    private LoadingView loadingView;
    private VerticalSeekbar mScrollSeekbar;
    private FolioWebView mWebview;
    private WebViewPager webViewPager;
    private TextSelectionSupport mTextSelectionSupport;
    private TextView mPagesLeftTextView, mMinutesLeftTextView;
    private FolioActivityCallback mActivityCallback;

    private int mTotalMinutes;
    private String mSelectedText;
    private Animation mFadeInAnimation, mFadeOutAnimation;

    private Link spineItem;
    private int mPosition = -1;
    private String mBookTitle;
    private String mEpubFileName = null;
    private boolean mIsPageReloaded;

    private String highlightStyle;

    private MediaController mediaController;
    private Config mConfig;
    private String mBookId;
    public SearchItem searchItemVisible;

    public static FolioPageFragment newInstance(int position, String bookTitle, Link spineRef, String bookId) {
        FolioPageFragment fragment = new FolioPageFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_FRAGMENT_FOLIO_POSITION, position);
        args.putString(KEY_FRAGMENT_FOLIO_BOOK_TITLE, bookTitle);
        args.putString(FolioReader.INTENT_BOOK_ID, bookId);
        args.putSerializable(SPINE_ITEM, spineRef);
        fragment.setArguments(args);
        return fragment;
    }

    private void chngeApplicationLanguage(String selectedLang )
    {
        String languageToLoad  = selectedLang; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config,
                getActivity().getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mConfig = AppUtil.getSavedConfig(getContext());
////        Config _conf = AppUtil.getSavedConfig(getContext());
        chngeApplicationLanguage(mConfig.getLanguage() );

        new WebView(getContext()).destroy();

        this.savedInstanceState = savedInstanceState;

        if (getActivity() instanceof FolioActivityCallback)
            mActivityCallback = (FolioActivityCallback) getActivity();

        EventBus.getDefault().register(this);

        mPosition = getArguments().getInt(KEY_FRAGMENT_FOLIO_POSITION);
        mBookTitle = getArguments().getString(KEY_FRAGMENT_FOLIO_BOOK_TITLE);
        mEpubFileName = getArguments().getString(KEY_FRAGMENT_EPUB_FILE_NAME);
        spineItem = (Link) getArguments().getSerializable(SPINE_ITEM);
        mBookId = getArguments().getString(FolioReader.INTENT_BOOK_ID);

        if (savedInstanceState != null) {
            searchItemVisible = savedInstanceState.getParcelable(BUNDLE_SEARCH_ITEM);
        }

        if (spineItem != null) {
            if (spineItem.properties.contains("media-overlay")) {
                mediaController = new MediaController(getActivity(), MediaController.MediaType.SMIL, this);
                hasMediaOverlay = true;
            } else {
                mediaController = new MediaController(getActivity(), MediaController.MediaType.TTS, this);
                mediaController.setTextToSpeech(getActivity());
            }
        }
        highlightStyle = HighlightImpl.HighlightStyle.classForStyle(HighlightImpl.HighlightStyle.Normal);
        mRootView = inflater.inflate(R.layout.folio_page_fragment, container, false);
        mPagesLeftTextView = (TextView) mRootView.findViewById(R.id.pagesLeft);
        mMinutesLeftTextView = (TextView) mRootView.findViewById(R.id.minutesLeft);


        //mConfig = AppUtil.getSavedConfig(getContext());

        loadingView = mRootView.findViewById(R.id.loadingView);
        initSeekbar();
        initAnimations();
        initWebView();
        updatePagesLeftTextBg();

        return mRootView;
    }

    private String getWebviewUrl() {
        return Constants.LOCALHOST + Uri.encode(mBookTitle) + "/" + spineItem.href;
    }

    /**
     * [EVENT BUS FUNCTION]
     * Function triggered from {@link MediaControllerFragment#initListeners()} when pause/play
     * button is clicked
     *
     * @param event of type {@link MediaOverlayPlayPauseEvent} contains if paused/played
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void pauseButtonClicked(MediaOverlayPlayPauseEvent event) {
        if (isAdded()
                && spineItem.href.equals(event.getHref())) {
            mediaController.stateChanged(event);
        }
    }

    /**
     * [EVENT BUS FUNCTION]
     * Function triggered from {@link MediaControllerFragment#initListeners()} when speed
     * change buttons are clicked
     *
     * @param event of type {@link MediaOverlaySpeedEvent} contains selected speed
     *              type HALF,ONE,ONE_HALF and TWO.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void speedChanged(MediaOverlaySpeedEvent event) {
        if (mediaController != null)
            mediaController.setSpeed(event.getSpeed());
    }

    /**
     * [EVENT BUS FUNCTION]
     * Function triggered from {@link MediaControllerFragment#initListeners()} when new
     * style is selected on button click.
     *
     * @param event of type {@link MediaOverlaySpeedEvent} contains selected style
     *              of type DEFAULT,UNDERLINE and BACKGROUND.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void styleChanged(MediaOverlayHighlightStyleEvent event) {
        if (isAdded()) {
            switch (event.getStyle()) {
                case DEFAULT:
                    highlightStyle =
                            HighlightImpl.HighlightStyle.classForStyle(HighlightImpl.HighlightStyle.Normal);
                    break;
                case UNDERLINE:
                    highlightStyle =
                            HighlightImpl.HighlightStyle.classForStyle(HighlightImpl.HighlightStyle.DottetUnderline);
                    break;
                case BACKGROUND:
                    highlightStyle =
                            HighlightImpl.HighlightStyle.classForStyle(HighlightImpl.HighlightStyle.TextColor);
                    break;
            }
            mWebview.loadUrl(String.format(getString(R.string.setmediaoverlaystyle), highlightStyle));
        }
    }

    /**
     * [EVENT BUS FUNCTION]
     * Function triggered when any EBook configuration is changed.
     *
     * @param reloadDataEvent empty POJO.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reload(ReloadDataEvent reloadDataEvent) {

        if (isCurrentFragment())
            getLastReadPosition();

        if (isAdded()) {
            loadingView.updateTheme();
            loadingView.show();
            mIsPageReloaded = true;
            setHtml(true);
            updatePagesLeftTextBg();
        }
    }

    /**
     * [EVENT BUS FUNCTION]
     * <p>
     * Function triggered when highlight is deleted and page is needed to
     * be updated.
     *
     * @param event empty POJO.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateHighlight(UpdateHighlightEvent event) {
        if (isAdded()) {
            this.rangy = HighlightUtil.generateRangyString(getPageName());
            loadRangy(mWebview, this.rangy);
        }
    }

    public void scrollToAnchorId(String href) {

        if (!TextUtils.isEmpty(href) && href.indexOf('#') != -1) {
            mAnchorId = href.substring(href.lastIndexOf('#') + 1);
            if (loadingView != null && loadingView.getVisibility() != View.VISIBLE) {
                loadingView.show();
                mWebview.loadUrl(String.format(getString(R.string.go_to_anchor), mAnchorId));
                mAnchorId = null;
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetCurrentIndex(RewindIndexEvent resetIndex) {
        if (isCurrentFragment()) {
            mWebview.loadUrl("javascript:rewindCurrentIndex()");
        }
    }

    @Override
    public void onReceiveHtml(String html) {
        if (isAdded()) {
            mHtmlString = html;
            setHtml(false);
        }
    }

    private void setHtml(boolean reloaded) {
        if (spineItem != null) {
            String ref = spineItem.href;
            if (!reloaded && spineItem.properties.contains("media-overlay")) {
                mediaController.setSMILItems(SMILParser.parseSMIL(mHtmlString));
                mediaController.setUpMediaPlayer(spineItem.mediaOverlay, spineItem.mediaOverlay.getAudioPath(spineItem.href), mBookTitle);
            }
            mConfig = AppUtil.getSavedConfig(getContext());

            String path = "";
            int forwardSlashLastIndex = ref.lastIndexOf('/');
            if (forwardSlashLastIndex != -1)
                path = ref.substring(0, forwardSlashLastIndex + 1);

            String mimeType;
            if (spineItem.typeLink.equalsIgnoreCase(getString(R.string.xhtml_mime_type))) {
                mimeType = getString(R.string.xhtml_mime_type);
            } else {
                mimeType = getString(R.string.html_mime_type);
            }

            mWebview.loadDataWithBaseURL(
                    Constants.LOCALHOST + mBookTitle + "/" + path,
                    HtmlUtil.getHtmlContent(getContext(), mHtmlString, mConfig),
                    mimeType,
                    "UTF-8",
                    null);
        }
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String getDirection() {
        return mActivityCallback.getDirection().toString();
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public int getTopDistraction() {
        return mActivityCallback.getTopDistraction();
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public int getBottomDistraction() {
        return mActivityCallback.getBottomDistraction();
    }

    public void scrollToLast() {

        boolean isPageLoading = loadingView == null || loadingView.getVisibility() == View.VISIBLE;
        Log.v(LOG_TAG, "-> scrollToLast -> isPageLoading = " + isPageLoading);

        if (!isPageLoading) {
            loadingView.show();
            mWebview.loadUrl("javascript:scrollToLast()");
        }
    }

    public void scrollToFirst() {

        boolean isPageLoading = loadingView == null || loadingView.getVisibility() == View.VISIBLE;
        Log.v(LOG_TAG, "-> scrollToFirst -> isPageLoading = " + isPageLoading);

        if (!isPageLoading) {
            loadingView.show();
            mWebview.loadUrl("javascript:scrollToFirst()");
        }
    }

    private void initWebView() {


        FrameLayout webViewLayout = mRootView.findViewById(R.id.webViewLayout);
        mWebview = webViewLayout.findViewById(R.id.folioWebView);
        mWebview.setParentFragment(this);
        webViewPager = webViewLayout.findViewById(R.id.webViewPager);

        if (getActivity() instanceof FolioActivityCallback)
            mWebview.setFolioActivityCallback((FolioActivityCallback) getActivity());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            WebView.setWebContentsDebuggingEnabled(true);

        setupScrollBar();
        mWebview.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int height =
                        (int) Math.floor(mWebview.getContentHeight() * mWebview.getScale());
                int webViewHeight = mWebview.getMeasuredHeight();
                mScrollSeekbar.setMaximum(height - webViewHeight);
            }
        });

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setVerticalScrollBarEnabled(false);
        mWebview.getSettings().setAllowFileAccess(true);

        mWebview.setHorizontalScrollBarEnabled(false);

        mWebview.addJavascriptInterface(this, "Highlight");
        mWebview.addJavascriptInterface(this, "FolioPageFragment");
        mWebview.addJavascriptInterface(webViewPager, "WebViewPager");
        mWebview.addJavascriptInterface(loadingView, "LoadingView");
        mWebview.addJavascriptInterface(mWebview, "FolioWebView");

        mWebview.setScrollListener(new FolioWebView.ScrollListener() {
            @Override
            public void onScrollChange(int percent) {

                mScrollSeekbar.setProgressAndThumb(percent);
                updatePagesLeftText(percent);
            }

        });

        mWebview.setWebViewClient(webViewClient);
        mWebview.setWebChromeClient(webChromeClient);

        mTextSelectionSupport = TextSelectionSupport.support(getActivity(), mWebview);
        mTextSelectionSupport.setSelectionListener(new TextSelectionSupport.SelectionListener() {
            @Override
            public void startSelection() {
            }

            @Override
            public void selectionChanged(String text) {
                mSelectedText = text;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWebview.loadUrl("javascript:alert(getRectForSelectedText())");
                    }
                });
            }

            @Override
            public void endSelection() {

            }
        });

        mWebview.getSettings().setDefaultTextEncodingName("utf-8");
        new HtmlTask(this).execute(getWebviewUrl());
    }

    int _PageNumber=0;
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {

            mWebview.loadUrl("javascript:getCompatMode()");
            mWebview.loadUrl("javascript:alert(getReadingTime())");

            if (!hasMediaOverlay)
                mWebview.loadUrl("javascript:wrappingSentencesWithinPTags()");

            if (mActivityCallback.getDirection() == Config.Direction.HORIZONTAL)
                mWebview.loadUrl("javascript:initHorizontalDirection()");

            view.loadUrl(String.format(getString(R.string.setmediaoverlaystyle),
                    HighlightImpl.HighlightStyle.classForStyle(
                            HighlightImpl.HighlightStyle.Normal)));

            String rangy = HighlightUtil.generateRangyString(getPageName());
            FolioPageFragment.this.rangy = rangy;
            if (!rangy.isEmpty())
                loadRangy(mWebview, rangy);

            if (mIsPageReloaded) {

                if (searchItemVisible != null) {
                    String escapedSearchQuery = searchItemVisible.getSearchQuery()
                            .replace("\"", "\\\"");
                    String call = String.format(getString(R.string.highlight_search_result),
                            escapedSearchQuery, searchItemVisible.getOccurrenceInChapter());
                    mWebview.loadUrl(call);

                } else if (isCurrentFragment()) {
                    mWebview.loadUrl(String.format("javascript:scrollToSpan(%b, %s)",
                            lastReadPosition.isUsingId(), lastReadPosition.getValue()));

                } else {
                    if (mPosition == mActivityCallback.getCurrentChapterIndex() - 1) {
                        // Scroll to last, the page before current page
                        mWebview.loadUrl("javascript:scrollToLast()");
                    } else {
                        // Make loading view invisible for all other fragments
                        loadingView.hide();
                    }
                }

                mIsPageReloaded = false;

            } else if (!TextUtils.isEmpty(mAnchorId)) {
                mWebview.loadUrl(String.format(getString(R.string.go_to_anchor), mAnchorId));
                mAnchorId = null;

            } else if (!TextUtils.isEmpty(highlightId)) {
                mWebview.loadUrl(String.format(getString(R.string.go_to_highlight), highlightId));
                highlightId = null;

            } else if (searchItemVisible != null) {
                String escapedSearchQuery = searchItemVisible.getSearchQuery()
                        .replace("\"", "\\\"");
                String call = String.format(getString(R.string.highlight_search_result),
                        escapedSearchQuery, searchItemVisible.getOccurrenceInChapter());
                mWebview.loadUrl(call);

            } else if (isCurrentFragment()) {

                ReadPosition readPosition;
                if (savedInstanceState == null) {
                    Log.v(LOG_TAG, "-> onPageFinished -> took from getEntryReadPosition");
                    readPosition = mActivityCallback.getEntryReadPosition();
                } else {
                    Log.v(LOG_TAG, "-> onPageFinished -> took from bundle");
                    readPosition = savedInstanceState.getParcelable(BUNDLE_READ_POSITION_CONFIG_CHANGE);
                    savedInstanceState.remove(BUNDLE_READ_POSITION_CONFIG_CHANGE);
                }

                if (readPosition != null) {
                    Log.v(LOG_TAG, "-> scrollToSpan -> " + readPosition.getValue());
                    mWebview.loadUrl(String.format("javascript:scrollToSpan(%b, %s)",
                            readPosition.isUsingId(), readPosition.getValue()));
                } else {
                    loadingView.hide();
                }

            } else {

                if (mPosition == mActivityCallback.getCurrentChapterIndex() - 1) {
                    // Scroll to last, the page before current page
                    mWebview.loadUrl("javascript:scrollToLast()");
                } else {
                    // Make loading view invisible for all other fragments
                    loadingView.hide();
                }
            }
        }



        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.isEmpty())
                return true;

            if (Uri.parse(url).getScheme().startsWith("highlight")) {
                final Pattern pattern = Pattern.compile(getString(R.string.pattern));
                try {
                    String htmlDecode = URLDecoder.decode(url, "UTF-8");
                    Matcher matcher = pattern.matcher(htmlDecode.substring(12));
                    if (matcher.matches()) {
                        double left = Double.parseDouble(matcher.group(1));
                        double top = Double.parseDouble(matcher.group(2));
                        double width = Double.parseDouble(matcher.group(3));
                        double height = Double.parseDouble(matcher.group(4));
                        onHighlight((int) (UiUtil.convertDpToPixel((float) left, getActivity())),
                                (int) (UiUtil.convertDpToPixel((float) top, getActivity())),
                                (int) (UiUtil.convertDpToPixel((float) width, getActivity())),
                                (int) (UiUtil.convertDpToPixel((float) height, getActivity())));
                    }
                } catch (UnsupportedEncodingException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            } else {
                boolean urlOfEpub = mActivityCallback.goToChapter(url);

                if (!urlOfEpub) {
                    // Otherwise, give the default behavior (open in browser)
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            }
            return true;
        }

        // prevent favicon.ico to be loaded automatically
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (url.toLowerCase().contains("/favicon.ico")) {
                try {
                    return new WebResourceResponse("image/png", null, null);
                } catch (Exception e) {
                    Log.e(LOG_TAG, "shouldInterceptRequest failed", e);
                }
            }
            return null;
        }

        // prevent favicon.ico to be loaded automatically
        @Override
        @SuppressLint("NewApi")
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if (!request.isForMainFrame()
                    && request.getUrl().getPath() != null
                    && request.getUrl().getPath().endsWith("/favicon.ico")) {
                try {
                    return new WebResourceResponse("image/png", null, null);
                } catch (Exception e) {
                    Log.e(LOG_TAG, "shouldInterceptRequest failed", e);
                }
            }
            return null;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    };

    private WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int progress) {
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            if (FolioPageFragment.this.isVisible()) {
                String rangyPattern = "\\d+\\$\\d+\\$\\d+\\$\\w+\\$";
                Pattern pattern = Pattern.compile(rangyPattern);
                Matcher matcher = pattern.matcher(message);
                if (matcher.matches()) {
                    HighlightImpl highlightImpl = HighLightTable.getHighlightForRangy(message);
                    if (HighLightTable.deleteHighlight(message)) {
                        String rangy = HighlightUtil.generateRangyString(getPageName());
                        loadRangy(view, rangy);
                        mTextSelectionSupport.endSelectionMode();
                        if (highlightImpl != null) {
                            HighlightUtil.sendHighlightBroadcastEvent(
                                    FolioPageFragment.this.getActivity().getApplicationContext(),
                                    highlightImpl,
                                    HighLight.HighLightAction.DELETE);
                        }
                    }
                } else if (TextUtils.isDigitsOnly(message)) {
                    try {
                        mTotalMinutes = Integer.parseInt(message);
                    } catch (NumberFormatException e) {
                        mTotalMinutes = 0;
                    }
                } else {
                    pattern = Pattern.compile(getString(R.string.pattern));
                    matcher = pattern.matcher(message);
                    if (matcher.matches()) {
                        double left = Double.parseDouble(matcher.group(1));
                        double top = Double.parseDouble(matcher.group(2));
                        double width = Double.parseDouble(matcher.group(3));
                        double height = Double.parseDouble(matcher.group(4));
                        showTextSelectionMenu((int) (UiUtil.convertDpToPixel((float) left,
                                getActivity())),
                                (int) (UiUtil.convertDpToPixel((float) top,
                                        getActivity())),
                                (int) (UiUtil.convertDpToPixel((float) width,
                                        getActivity())),
                                (int) (UiUtil.convertDpToPixel((float) height,
                                        getActivity())));
                    } else {
                        // to handle TTS playback when highlight is deleted.
                        Pattern p = Pattern.compile("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}");
                        if (!p.matcher(message).matches() && (!message.equals("undefined")) && isCurrentFragment()) {
                            mediaController.speakAudio(message);
                        }
                    }
                }
                result.confirm();
            }
            return true;
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        Log.v(LOG_TAG, "-> onStop -> " + spineItem.originalHref + " -> " + isCurrentFragment());

        mediaController.stop();
        //TODO save last media overlay item

        if (isCurrentFragment())
            getLastReadPosition();
    }

    /**
     * Calls the /assets/js/Bridge.js#getFirstVisibleSpan(boolean)
     */
    public ReadPosition getLastReadPosition() {
        Log.v(LOG_TAG, "-> getLastReadPosition -> " + spineItem.originalHref);

        try {
            synchronized (this) {
                boolean isHorizontal = mActivityCallback.getDirection() ==
                        Config.Direction.HORIZONTAL;
                mWebview.loadUrl("javascript:getFirstVisibleSpan(" + isHorizontal + ")");

                wait(2000);
            }
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, "-> ", e);
        }

        return lastReadPosition;
    }

    /**
     * Callback method called from /assets/js/Bridge.js#getFirstVisibleSpan(boolean)
     * and then ReadPositionImpl is broadcast to {@link FolioReader#readPositionReceiver}
     *
     * @param usingId if span tag has id then true or else false
     * @param value   if usingId true then span id else span index
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void storeFirstVisibleSpan(boolean usingId, String value) {

        synchronized (this) {
            lastReadPosition = new ReadPositionImpl(mBookId, spineItem.getId(),
                    spineItem.getOriginalHref(), mPosition, usingId, value);
            Intent intent = new Intent(FolioReader.ACTION_SAVE_READ_POSITION);
            intent.putExtra(FolioReader.EXTRA_READ_POSITION, lastReadPosition);
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

            notify();
        }
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public void setHorizontalPageCount(int horizontalPageCount) {
        Log.v(LOG_TAG, "-> setHorizontalPageCount = " + horizontalPageCount
                + " -> " + spineItem.originalHref);

        mWebview.setHorizontalPageCount(horizontalPageCount);
    }

    private void loadRangy(WebView view, String rangy) {
        view.loadUrl(String.format("javascript:if(typeof ssReader !== \"undefined\"){ssReader.setHighlights('%s');}", rangy));
    }

    private void setupScrollBar() {
        UiUtil.setColorIntToDrawable(mConfig.getThemeColor(), mScrollSeekbar.getProgressDrawable());
        Drawable thumbDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.icons_sroll);
        UiUtil.setColorIntToDrawable(mConfig.getThemeColor(), thumbDrawable);
        mScrollSeekbar.setThumb(thumbDrawable);
    }

    private void initSeekbar() {
        mScrollSeekbar = (VerticalSeekbar) mRootView.findViewById(R.id.scrollSeekbar);
        mScrollSeekbar.getProgressDrawable()
                .setColorFilter(getResources()
                                .getColor(R.color.app_green),
                        PorterDuff.Mode.SRC_IN);
    }

    private void updatePagesLeftTextBg() {

        if (mConfig.isNightMode()) {
            mRootView.findViewById(R.id.indicatorLayout)
                    .setBackgroundColor(Color.parseColor("#131313"));
        } else {
            mRootView.findViewById(R.id.indicatorLayout)
                    .setBackgroundColor(Color.WHITE);
        }
    }
    //int currentPage = 0;
    private void updatePagesLeftText(int scrollY) {
        try {
            
            int currentPage = (int) (Math.ceil((double) scrollY / mWebview.getWebViewHeight()) + 1);

            int totalPages =
                    (int) Math.ceil((double) mWebview.getContentHeightVal()
                            / mWebview.getWebViewHeight());
            int pagesRemaining = totalPages - currentPage;
            String pagesRemainingStrFormat =
                    pagesRemaining > 1 ?
                            getString(R.string.pages_left) : getString(R.string.page_left);
            String pagesRemainingStr = String.format(Locale.US,
                    pagesRemainingStrFormat, currentPage);

            int minutesRemaining =
                    (int) Math.ceil((double) (pagesRemaining * mTotalMinutes) / totalPages);
            String minutesRemainingStr;
            if (minutesRemaining > 1) {
                minutesRemainingStr =
                        String.format(Locale.US, getString(R.string.minutes_left),
                                minutesRemaining);
            } else if (minutesRemaining == 1) {
                minutesRemainingStr =
                        String.format(Locale.US, getString(R.string.minute_left),
                                minutesRemaining);
            } else {
                minutesRemainingStr = getString(R.string.less_than_minute);
            }

            //mMinutesLeftTextView.setText(minutesRemainingStr);
            mPagesLeftTextView.setText(pagesRemainingStr);
        } catch (java.lang.ArithmeticException | IllegalStateException exp) {
            Log.e("divide error", exp.toString());
        }
    }

    private void initAnimations() {
        mFadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);
        mFadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mScrollSeekbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeOutSeekBarIfVisible();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mFadeOutAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fadeout);
        mFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mScrollSeekbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void fadeInSeekBarIfInvisible() {
        if (mScrollSeekbar.getVisibility() == View.INVISIBLE ||
                mScrollSeekbar.getVisibility() == View.GONE) {
            mScrollSeekbar.startAnimation(mFadeInAnimation);
        }
    }

    public void fadeOutSeekBarIfVisible() {
        if (mScrollSeekbar.getVisibility() == View.VISIBLE) {
            mScrollSeekbar.startAnimation(mFadeOutAnimation);
        }
    }

    @Override
    public void onDestroyView() {
        mFadeInAnimation.setAnimationListener(null);
        mFadeOutAnimation.setAnimationListener(null);
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    /**
     * If called, this method will occur after onStop() for applications targeting platforms
     * starting with Build.VERSION_CODES.P. For applications targeting earlier platform versions
     * this method will occur before onStop() and there are no guarantees about whether it will
     * occur before or after onPause()
     *
     * @see Activity#onSaveInstanceState(Bundle) of Build.VERSION_CODES.P
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.outState = outState;

        if (isCurrentFragment())
            Log.v(LOG_TAG, "-> onSaveInstanceState");

        outState.putParcelable(BUNDLE_SEARCH_ITEM, searchItemVisible);
    }

    public void highlight(HighlightImpl.HighlightStyle style, boolean isCreated) {
        if (isCreated) {
            mWebview.loadUrl(String.format("javascript:if(typeof ssReader !== \"undefined\"){ssReader.highlightSelection('%s');}", HighlightImpl.HighlightStyle.classForStyle(style)));
        } else {
            mWebview.loadUrl(String.format("javascript:setHighlightStyle('%s')", "highlight_" + HighlightImpl.HighlightStyle.classForStyle(style)));
        }
    }

    public void highlightRemove() {
        mWebview.loadUrl("javascript:alert(removeThisHighlight())");
    }

    public void showTextSelectionMenu(int x, int y, final int width, final int height) {
        final ViewGroup root =
                (ViewGroup) getActivity().getWindow()
                        .getDecorView().findViewById(android.R.id.content);
        final View view = new View(getActivity());
        ViewGroup.LayoutParams lp =new ViewGroup.LayoutParams(width, height);
        lp.resolveLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        view.setLayoutParams(lp);
        view.setBackgroundColor(Color.TRANSPARENT);

        root.addView(view);

        view.setX(x);
        view.setY(y);
        final QuickAction quickAction =
                new QuickAction(getActivity(), QuickAction.VERTICAL);

        quickAction.addActionItem(new ActionItem(ACTION_ID_COPY,
                getString(R.string.copy)));
        quickAction.addActionItem(new ActionItem(ACTION_ID_HIGHLIGHT,
                getString(R.string.highlight)));
//        if (!mSelectedText.trim().contains(" ")) {
//            quickAction.addActionItem(new ActionItem(ACTION_ID_DEFINE,
//                    getString(R.string.define)));
//        }
        quickAction.addActionItem(new ActionItem(ACTION_ID_SHARE,
                getString(R.string.share)));
        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                quickAction.dismiss();
                root.removeView(view);
                onTextSelectionActionItemClicked(actionId, view, width, height);
            }
        });
        quickAction.show(view, width, height);
    }

    private void onTextSelectionActionItemClicked(int actionId, View view, int width, int height) {
        if (actionId == ACTION_ID_COPY) {
            UiUtil.copyToClipboard(getActivity(), mSelectedText);
            Toast.makeText(getActivity(), getString(R.string.copied), Toast.LENGTH_SHORT).show();
            mTextSelectionSupport.endSelectionMode();
        } else if (actionId == ACTION_ID_SHARE) {
            UiUtil.share(getActivity(), mSelectedText);
        } else if (actionId == ACTION_ID_DEFINE) {
            showDictDialog(mSelectedText);
            mTextSelectionSupport.endSelectionMode();
        } else if (actionId == ACTION_ID_HIGHLIGHT) {
            onHighlight(view, width, height, true);
        }
    }

    private void showDictDialog(String mSelectedText) {
        DictionaryFragment dictionaryFragment = new DictionaryFragment();
        Bundle b = new Bundle();
        b.putString(Constants.SELECTED_WORD, mSelectedText);
        dictionaryFragment.setArguments(b);
        dictionaryFragment.show(getFragmentManager(), DictionaryFragment.class.getName());
    }

    private void onHighlight(int x, int y, int width, int height) {
        final View view = new View(getActivity());
        view.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        view.setBackgroundColor(Color.TRANSPARENT);
        view.setX(x);
        view.setY(y);
        onHighlight(view, width, height, false);
    }

    private void onHighlight(final View view, int width, int height, final boolean isCreated) {
        ViewGroup root =
                (ViewGroup) getActivity().getWindow().
                        getDecorView().findViewById(android.R.id.content);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent == null) {
            root.addView(view);
        } else {
            final int index = parent.indexOfChild(view);
            parent.removeView(view);
            parent.addView(view, index);
        }

        final QuickAction quickAction = new QuickAction(getActivity(), QuickAction.HORIZONTAL);
        quickAction.addActionItem(new ActionItem(ACTION_ID_HIGHLIGHT_COLOR,
                getResources().getDrawable(R.drawable.colors_marker)));
        quickAction.addActionItem(new ActionItem(ACTION_ID_DELETE,
                getResources().getDrawable(R.drawable.ic_action_discard)));
        quickAction.addActionItem(new ActionItem(ACTION_ID_SHARE,
                getResources().getDrawable(R.drawable.ic_action_share)));
        final ViewGroup finalRoot = root;
        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                quickAction.dismiss();
                finalRoot.removeView(view);
                onHighlightActionItemClicked(actionId, view, isCreated);
            }
        });
        quickAction.show(view, width, height);
    }

    private void onHighlightActionItemClicked(int actionId, View view, boolean isCreated) {
        if (actionId == ACTION_ID_HIGHLIGHT_COLOR) {
            onHighlightColors(view, isCreated);
        } else if (actionId == ACTION_ID_SHARE) {
            UiUtil.share(getActivity(), mSelectedText);
            mTextSelectionSupport.endSelectionMode();
        } else if (actionId == ACTION_ID_DELETE) {
            highlightRemove();
        }
    }

    private void onHighlightColors(final View view, final boolean isCreated) {
        ViewGroup root =
                (ViewGroup) getActivity().getWindow()
                        .getDecorView().findViewById(android.R.id.content);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent == null) {
            root.addView(view);
        } else {
            final int index = parent.indexOfChild(view);
            parent.removeView(view);
            parent.addView(view, index);
        }

        final QuickAction quickAction = new QuickAction(getActivity(), QuickAction.HORIZONTAL);
        quickAction.addActionItem(new ActionItem(ACTION_ID_HIGHLIGHT_YELLOW,
                getResources().getDrawable(R.drawable.ic_yellow_marker)));
        quickAction.addActionItem(new ActionItem(ACTION_ID_HIGHLIGHT_GREEN,
                getResources().getDrawable(R.drawable.ic_green_marker)));
        quickAction.addActionItem(new ActionItem(ACTION_ID_HIGHLIGHT_BLUE,
                getResources().getDrawable(R.drawable.ic_blue_marker)));
        quickAction.addActionItem(new ActionItem(ACTION_ID_HIGHLIGHT_PINK,
                getResources().getDrawable(R.drawable.ic_pink_marker)));
        quickAction.addActionItem(new ActionItem(ACTION_ID_HIGHLIGHT_UNDERLINE,
                getResources().getDrawable(R.drawable.ic_underline_marker)));
        final ViewGroup finalRoot = root;
        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                quickAction.dismiss();
                finalRoot.removeView(view);
                onHighlightColorsActionItemClicked(actionId, view, isCreated);
            }
        });
        quickAction.show(view);
    }

    private void onHighlightColorsActionItemClicked(int actionId, View view, boolean isCreated) {
        if (actionId == ACTION_ID_HIGHLIGHT_YELLOW) {
            highlight(HighlightImpl.HighlightStyle.Yellow, isCreated);
        } else if (actionId == ACTION_ID_HIGHLIGHT_GREEN) {
            highlight(HighlightImpl.HighlightStyle.Green, isCreated);
        } else if (actionId == ACTION_ID_HIGHLIGHT_BLUE) {
            highlight(HighlightImpl.HighlightStyle.Blue, isCreated);
        } else if (actionId == ACTION_ID_HIGHLIGHT_PINK) {
            highlight(HighlightImpl.HighlightStyle.Pink, isCreated);
        } else if (actionId == ACTION_ID_HIGHLIGHT_UNDERLINE) {
            highlight(HighlightImpl.HighlightStyle.Underline, isCreated);
        }
        mTextSelectionSupport.endSelectionMode();
    }

    @Override
    public void resetCurrentIndex() {
        if (isCurrentFragment()) {
            mWebview.loadUrl("javascript:rewindCurrentIndex()");
        }
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public void onReceiveHighlights(String html) {
        if (html != null) {
            rangy = HighlightUtil.createHighlightRangy(getActivity().getApplicationContext(),
                    html,
                    mBookId,
                    getPageName(),
                    mPosition,
                    rangy);
        }
    }

    private String getPageName() {
        return mBookTitle + "$" + spineItem.href;
    }

    @Override
    public void highLightText(String fragmentId) {
        mWebview.loadUrl(String.format(getString(R.string.audio_mark_id), fragmentId));
    }

    @Override
    public void highLightTTS() {
        mWebview.loadUrl("javascript:alert(getSentenceWithIndex('epub-media-overlay-playing'))");
    }

    @JavascriptInterface
    public void getUpdatedHighlightId(String id, String style) {
        if (id != null) {
            HighlightImpl highlightImpl = HighLightTable.updateHighlightStyle(id, style);
            if (highlightImpl != null) {
                HighlightUtil.sendHighlightBroadcastEvent(
                        getActivity().getApplicationContext(),
                        highlightImpl,
                        HighLight.HighLightAction.MODIFY);
            }
            final String rangyString = HighlightUtil.generateRangyString(getPageName());
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    loadRangy(mWebview, rangyString);
                }
            });

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (isCurrentFragment()) {
            if (outState != null)
                outState.putParcelable(BUNDLE_READ_POSITION_CONFIG_CHANGE, lastReadPosition);
            if (getActivity() != null && !getActivity().isFinishing())
                mActivityCallback.storeLastReadPosition(lastReadPosition);
        }
        if (mWebview != null) mWebview.destroy();
    }

    private boolean isCurrentFragment() {
//        Log.d(LOG_TAG, "-> isCurrentFragment -> "
//                + ", isAdded = " + isAdded()
//                + ", mActivityCallback.getCurrentChapterIndex() = " + mActivityCallback.getCurrentChapterIndex()
//                + ", mPosition = " + mPosition);
        return isAdded() && mActivityCallback.getCurrentChapterIndex() == mPosition;
    }

    @Override
    public void onError() {
    }

    public void scrollToHighlightId(String highlightId) {
        this.highlightId = highlightId;

        if (loadingView != null && loadingView.getVisibility() != View.VISIBLE) {
            loadingView.show();
            mWebview.loadUrl(String.format(getString(R.string.go_to_highlight), highlightId));
            this.highlightId = null;
        }
    }

    public void highlightSearchItem(@NonNull SearchItem searchItem) {
        Log.v(LOG_TAG, "-> highlightSearchItem");
        this.searchItemVisible = searchItem;

        if (loadingView != null && loadingView.getVisibility() != View.VISIBLE) {
            loadingView.show();
            String escapedSearchQuery = searchItem.getSearchQuery().replace("\"", "\\\"");
            String call = String.format(getString(R.string.highlight_search_result),
                    escapedSearchQuery, searchItem.getOccurrenceInChapter());
            mWebview.loadUrl(call);
        }
    }

    public void resetSearchResults() {
        Log.v(LOG_TAG, "-> resetSearchResults -> " + spineItem.originalHref);
        mWebview.loadUrl(getString(R.string.reset_search_results));
        searchItemVisible = null;
    }
}
