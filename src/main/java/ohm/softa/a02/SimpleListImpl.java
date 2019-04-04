package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object>{

    Element head;
    int size;

    public SimpleListImpl(){
        head = null;
        size = 0;
    }

    @Override
    public void add(Object o) {
        if(head == null){
            head = new Element(o);
        }else{
            Element temp = head;
            while(temp.getNext()!= null){
                temp = temp.getNext();
            }
            temp.next = new Element(o);
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList result = new SimpleListImpl();
        for(Object o : this){
            if(filter.include(o)){
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleListIterator();
    }

    public class SimpleListIterator implements Iterator<Object>{
        public Element current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object temp = current.getItem();
            current = current.getNext();
            return temp;
        }
    }

    private static class Element{
        private Object item;
        private Element next;

        Element(Object o){
            this.item = o;
            this.next = null;
        }

        public Element getNext() {
            return next;
        }

        public Object getItem() {
            return item;
        }

        public void setNext(Element next){
            this.next = next;

        }
    }

}
