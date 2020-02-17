import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Marco Gonzalez (mag0089@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      T min = iter.next();
      for (T obj : coll) {
         if (comp.compare(obj, min) < 0) {
            min = obj;
         }    
      }
      
      
      return min;
      
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      T max = iter.next();
      for (T obj : coll) {
         if (comp.compare(obj, max) > 0) {
            max = obj;
         }    
      }
      
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      
      
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      if ((k < 1) || (k > coll.size())) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      
      if ((k == 1) && (1 == coll.size())) {
         return iter.next();
      }
      
      ArrayList<T> distinctList = new ArrayList<T>(0);
      ArrayList<T> copy = new ArrayList<T>(coll);
      
      
      for (T temp : copy) {
         if (!distinctList.contains(temp)) {
            distinctList.add(temp);
         }   
      }
      
      if (k > distinctList.size()) {
         throw new NoSuchElementException();   
      }
      
      if ((distinctList.size() == 1) && (k == 1)) {
         return iter.next();
      }
      
      if ((distinctList.size() == 2) && (k == 2)) {
         iter.next();   
         return iter.next();
      }
      
      java.util.Collections.sort(distinctList, comp);
      
      return distinctList.get(k - 1);
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      
      
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      if ((k < 1) || (k > coll.size())) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      
      if ((k == 1) && (1 == coll.size())) {
         return iter.next();
      }
      
      ArrayList<T> distinctList = new ArrayList<T>();
      
      for (T temp : coll) {
         if (!distinctList.contains(temp)) {
            distinctList.add(temp);
         }   
      }
      
      if (k > distinctList.size()) {
         throw new NoSuchElementException();   
      }
      
      if ((distinctList.size() == 1) && (k == 1)) {
         return iter.next();
      }
      
      if ((distinctList.size() == 2) && (k == 2)) {
         return iter.next();
      }
      
      java.util.Collections.sort(distinctList, comp);
      
      return distinctList.get(distinctList.size() - k);
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> newList = new ArrayList<T>(coll);
      ArrayList<T> newList2 = new ArrayList<T>(0);
      
      for (T temp : newList) {
         if ((comp.compare(temp, low)) >= 0 && (comp.compare(temp, high) <= 0)) {
            newList2.add(temp);
         }
      }
      
      if (newList2.size() == 0) {
         throw new NoSuchElementException();   
      }
      
      return newList2;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> newList = new ArrayList<T>(0);
      
      for (T temp : coll) {
         if ((comp.compare(temp, key)) >= 0 ) {
            newList.add(temp);
         }
      }
      
      if (newList.size() == 0) {
         throw new NoSuchElementException();   
      }
      
      Iterator<T> iter = newList.iterator();
      T minCeiling = iter.next();
      for (T obj : newList) {
         if (comp.compare(obj, minCeiling) < 0) {
            minCeiling = obj;
         }    
      }
      
      
      return minCeiling;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> newList = new ArrayList<T>(0);
      
      for (T temp : coll) {
         if ((comp.compare(temp, key)) <= 0 ) {
            newList.add(temp);
         }
      }
      
      if (newList.size() == 0) {
         throw new NoSuchElementException();   
      }
      
      Iterator<T> iter = newList.iterator();
      T maxCeiling = iter.next();
      for (T obj : newList) {
         if (comp.compare(obj, maxCeiling) > 0) {
            maxCeiling = obj;
         }    
      }
      
      return maxCeiling;
   }

}
