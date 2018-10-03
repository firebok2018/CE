package musicp.firebok.com.music.lastfm;

/**
 * An <code>Image</code> contains metadata and URLs for an artist's image.
 * Metadata contains title, votes, format and other. Images are available in
 * various sizes, see {@link ImageSize} for all sizes.
 *
 * @author Janni Kovacs
 * @see ImageSize
 * @see Artist#getImages(String, String)
 */
public class Image extends ImageHolder {

    final static ItemFactory<Image> FACTORY = new ImageFactory();

    private String url;

    private Image() {
    }

    public String getUrl() {
        return url;
    }

    private static class ImageFactory implements ItemFactory<Image> {
        @Override
        public Image createItemFromElement(final DomElement element) {
            final Image i = new Image();
            i.url = element.getChildText("url");
            loadImages(i, element);
            return i;
        }
    }
}
