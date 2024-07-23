// This is the Iterator class providing the abstract
// notion of iterating through a collection.
interface Iterator {
 public boolean hasNext();
 public Object next();
}
// This is the Container class providing an iterator to
// sequence through its contents.
interface Container {
 public Iterator getIterator();
}

// This container class is a box of numbers
class NumberBox implements Container {
 private int [] data;
 private int size;
 private int count;
 public NumberBox(int size) {
  this.size = size;
  data = new int[size];
  count = 0;
}
 public boolean store(int val) {
  if(count<size) {
   data[count++] = val;
   return true;
  } else
   return false;
 }

public class BoxIterator implements Iterator {
  private int idx;
  public BoxIterator() { idx = 0; }
  public boolean hasNext() {
   if(idx<count)
    return true;
   else
    return false;
  }
  public Object next() { return data[idx++]; }
 }
 public Iterator getIterator() {
  return new BoxIterator();
} }

// This container class is bucket of strings
class StringBucket implements Container {
 private String [] data;
 private int size;
 private int count;
 public StringBucket(int size) {
  this.size = size;
  data = new String[size];
  count = 0;
}
 public boolean store(String val) {
  if(count<size) {
   data[count++] = val;
   return true;
  } else
   return false;
 }

public class BucketIterator implements Iterator {
  private int idx;
  public BucketIterator() { idx = 0; }
  public boolean hasNext() {
   if(idx<count)
    return true;
   else
    return false;
  }
  public Object next() { return data[idx++]; }
 }
 public Iterator getIterator() {
  return new BucketIterator();
} }

public class CollectionIterator {
 public static void main(String [] args) {
  // create a new box of numbers
  NumberBox nbox = new NumberBox(10);
  nbox.store(1); nbox.store(3); nbox.store(5);
  nbox.store(6); nbox.store(8); nbox.store(10);
  nbox.store(12); nbox.store(13);
  // create an iterator of the box
  Iterator iter = nbox.getIterator();
  while(iter.hasNext())
   System.out.println(iter.next());

 // create a new bucket of strings
  StringBucket sbin = new StringBucket(10);
  sbin.store("apple"); sbin.store("hp"); sbin.store("asus");
  sbin.store("dell"); sbin.store("acer"); sbin.store("lenovo");
  sbin.store("ewis"); sbin.store("toshiba");
  // use the same iterator for the bucket
  iter = sbin.getIterator();
  while(iter.hasNext())
   System.out.println(iter.next());
 }
}
