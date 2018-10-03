package musicp.firebok.com.music.lastfm;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>DomElement</code> wraps around an {@link Element} and provides
 * convenience methods.
 *
 * @author Janni Kovacs
 */
public class DomElement {
    private final Element e;

    /**
     * Creates a new wrapper around the given {@link Element}.
     *
     * @param elem An w3c Element
     */
    public DomElement(final Element elem) {
        e = elem;
    }

    /**
     * @return the original Element
     */
    public Element getElement() {
        return e;
    }

    /**
     * Tests if this element has an attribute with the specified name.
     *
     * @param name Name of the attribute.
     * @return <code>true</code> if this element has an attribute with the
     *         specified name.
     */
    public boolean hasAttribute(final String name) {
        return e.hasAttribute(name);
    }

    /**
     * Returns the attribute value to a given attribute name or
     * <code>null</code> if the attribute doesn't exist.
     *
     * @param name The attribute's name
     * @return Attribute value or <code>null</code>
     */
    public String getAttribute(final String name) {
        return e.hasAttribute(name) ? e.getAttribute(name) : null;
    }

    /**
     * @return the text content of the element
     */
    public String getText() {
        // XXX e.getTextContent() doesn't exsist under Android (Lukasz
        // Wisniewski)
        // / getTextContent() is now available in at least Android 2.2 if not
        // earlier, so we'll keep using that
        // return e.hasChildNodes() ? e.getFirstChild().getNodeValue() : null;
        return e.getTextContent();
    }

    /**
     * Checks if this element has a child element with the given name.
     *
     * @param name The child's name
     * @return <code>true</code> if this element has a child element with the
     *         given name
     */
    public boolean hasChild(final String name) {
        final NodeList list = e.getElementsByTagName(name);
        for (int i = 0, j = list.getLength(); i < j; i++) {
            final Node item = list.item(i);
            if (item.getParentNode() == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the child element with the given name or <code>null</code> if it
     * doesn't exist.
     *
     * @param name The child's name
     * @return the child element or <code>null</code>
     */
    public DomElement getChild(final String name) {
        final NodeList list = e.getElementsByTagName(name);
        if (list.getLength() == 0) {
            return null;
        }
        for (int i = 0, j = list.getLength(); i < j; i++) {
            final Node item = list.item(i);
            if (item.getParentNode() == e) {
                return new DomElement((Element)item);
            }
        }
        return null;
    }

    /**
     * Returns the text content of a child node with the given name. If no such
     * child exists or the child does not have text content, <code>null</code>
     * is returned.
     *
     * @param name The child's name
     * @return the child's text content or <code>null</code>
     */
    public String getChildText(final String name) {
        final DomElement child = getChild(name);
        return child != null ? child.getText() : null;
    }

    /**
     * @return all children of this element
     */
    public List<DomElement> getChildren() {
        return getChildren("*");
    }

    /**
     * Returns all children of this element with the given tag name.
     *
     * @param name The children's tag name
     * @return all matching children
     */
    public List<DomElement> getChildren(final String name) {
        final List<DomElement> l = new ArrayList<DomElement>();
        final NodeList list = e.getElementsByTagName(name);
        for (int i = 0; i < list.getLength(); i++) {
            final Node node = list.item(i);
            if (node.getParentNode() == e) {
                l.add(new DomElement((Element)node));
            }
        }
        return l;
    }

    /**
     * Returns this element's tag name.
     *
     * @return the tag name
     */
    public String getTagName() {
        return e.getTagName();
    }
}
