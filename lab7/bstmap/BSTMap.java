package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{
    private BSTNode root;
    private int size;
    private class BSTNode {
        K key;
        V val;
        BSTNode left;
        BSTNode right;
        BSTNode parent;

        public BSTNode(K k, V v) {
            key = k;
            val = v;
        }

        public BSTNode(K k, V v, BSTNode par) {
            key = k;
            val = v;
            parent = par;
        }
        boolean has(K k) {
            if (key.equals(k)) {
                return true;
            }

            if (key.compareTo(k) < 0) {
                if (left == null) {
                    return false;
                }
                return left.has(k);
            }

            if (right == null) {
                return false;
            }
            return right.has(k);
        }

        /**
         * Assume this method is called if the subtree
         * rooted at the caller contains `key`
         * @param k
         * @return
         */
        V get(K k) {
            if (key.compareTo(k) < 0) {
                return left.get(k);
            } else if (key.compareTo(k) > 0) {
                return right.get(k);
            }
            return val;
        }

        void put(K k, V v) {
            if (key.compareTo(k) < 0) {
                if (left != null) {
                    left.put(k, v);
                } else {
                    left = new BSTNode(k, v, this);
                }
            } else if (key.compareTo(k) > 0) {
                if (right != null) {
                    right.put(k, v);
                } else {
                    right = new BSTNode(k, v, this);
                }
            }

            // base case found key to insert val
            this.val = v;
        }

        V remove(K key) {
            return null;
        }

    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public void printInOrder() {
        for (K k: this) {
            System.out.println(k);
        }
//        if (root != null) {
//            root.
//        }
    }
    @Override
    public boolean containsKey(K key) {
        return !(root == null) && root.has(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null; // if bst doesn't contain key return null
        }
        return root.get(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value);
        } else {
            root.put(key, value);
        }

        size++;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
