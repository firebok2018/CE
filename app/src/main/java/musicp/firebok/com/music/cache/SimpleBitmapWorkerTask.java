package musicp.firebok.com.music.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;


/**
 * The actual {@link android.os.AsyncTask} that will process the image.
 */
public class SimpleBitmapWorkerTask extends BitmapWorkerTask<String, Void, TransitionDrawable> {

    /**
     * Constructor of <code>BitmapWorkerTask</code>
     *
     * @param key the key of the image to store to
     * @param imageView The {@link ImageView} to use.
     * @param imageType The type of image URL to fetch for.
     * @param fromDrawable what drawable to transition from
     */
    public SimpleBitmapWorkerTask(final String key, final ImageView imageView, final ImageType imageType,
                            final Drawable fromDrawable, final Context context) {
        super(key, imageView, imageType, fromDrawable, context);
    }

    /**
     * Constructor of <code>BitmapWorkerTask</code>
     *
     * @param key the key of the image to store to
     * @param imageView The {@link ImageView} to use.
     * @param imageType The type of image URL to fetch for.
     * @param fromDrawable what drawable to transition from
     * @param scaleImgToView flag to scale the bitmap to the image view bounds
     */
    public SimpleBitmapWorkerTask(final String key, final ImageView imageView, final ImageType imageType,
                                  final Drawable fromDrawable, final Context context, final boolean scaleImgToView) {
        super(key, imageView, imageType, fromDrawable, context, scaleImgToView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TransitionDrawable doInBackground(final String... params) {
        if (isCancelled()) {
            return null;
        }

        final Bitmap bitmap = getBitmapInBackground(params);
        if (mScaleImgToView) {
            Bitmap scaledBitmap = ImageUtils.scaleBitmapForImageView(bitmap, getAttachedImageView());
            return createImageTransitionDrawable(scaledBitmap);
        }
        else
            return createImageTransitionDrawable(bitmap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPostExecute(TransitionDrawable transitionDrawable) {
        final ImageView imageView = getAttachedImageView();
        if (transitionDrawable != null && imageView != null) {
            imageView.setImageDrawable(transitionDrawable);
        } else if (imageView != null) {
            imageView.setImageDrawable(mFromDrawable);
        }
    }
}
