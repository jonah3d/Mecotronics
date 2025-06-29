/*
 * EmployeeLogin.java
 *
 * Generated with Matisse Schema Definition Language 9.1.12
 * Generation date: Thu Jun 26 05:07:10 2025
 */

// Note: the package and extends declarations are generated by mt_sdl, do not modify them

package com.joe.models;

import com.matisse.reflect.*;

/**
 * <code>EmployeeLogin</code> is a schema class generated by <code>mt_sdl</code>.
 * Any user-written classes will be found at the end of the file, after
 * the '// END of Matisse SDL Generated Code' comment.
 * Attribute types, default values, and relationship minimum and maximum
 * cardinality are stored in the database itself, not in this source code.
 * For more information, see <i>Getting Started with MATISSE</i>.
 */
public class EmployeeLogin extends com.matisse.reflect.MtObject {

    // BEGIN Matisse SDL Generated Code
    // DO NOT MODIFY UNTIL THE 'END of Matisse SDL Generated Code' MARK BELOW
    /*
     * Generated with Matisse Schema Definition Language 9.1.12
     * Generation Date: Thu Jun 26 05:07:10 2025
     */

    /*
     * Class variables and methods
     */

    /** Class <code>EmployeeLogin</code> cache ID */
    private static int CID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtClass.Loader("com.joe.models.EmployeeLogin"));

    /**
     * Gets the <code>EmployeeLogin</code> class descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db a database
     * @return a class descriptor
     */
    public static com.matisse.reflect.MtClass getClass(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtClass)db.getCachedObject(CID);
    }

    /**
     * Factory constructor. This constructor is called by <code>MtObjectFactory</code>.
     * It is public for technical reasons but is not intended to be called
     * directly by user methods.
     * @param db a database
     * @param mtOid an existing object ID in the database
     */
    public EmployeeLogin(com.matisse.MtDatabase db, int mtOid)  {
        super(db, mtOid);
    }

    /**
     * Cascaded constructor, used by subclasses to create a new object in the database.
     * It is protected for technical reasons but is not intended to be called
     * directly by user methods.
     * @param mtCls a class descriptor (the class to instantiate)
     */
    protected EmployeeLogin(com.matisse.reflect.MtClass mtCls)  {
        super(mtCls);
    }

    /**
     * Opens an iterator on all instances of this class (and its subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> instanceIterator(com.matisse.MtDatabase db) {
        return getClass(db).<E>instanceIterator(EmployeeLogin.class);
    }

    /**
     * Opens an iterator on all instances of this class (and its subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @param numObjPerBuffer maximum number of objects to fetch from the server at a time
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> instanceIterator(com.matisse.MtDatabase db, int numObjPerBuffer) {
        return getClass(db).<E>instanceIterator(numObjPerBuffer, EmployeeLogin.class);
    }

    /**
     * Counts the number of instances of this class (and its subclasses).
     * @param db a database
     * @return total number of instances
     */
    public static long getInstanceNumber(com.matisse.MtDatabase db) {
        return getClass(db).getInstanceNumber();
    }

    /**
     * Opens an iterator on all own instances of this class (excluding subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> ownInstanceIterator(com.matisse.MtDatabase db) {
        return getClass(db).<E>ownInstanceIterator(EmployeeLogin.class);
    }

    /**
     * Opens an iterator on all own instances of this class (excluding subclasses).
     * @param <E> a MtObject class     * @param db a database
     * @param numObjPerBuffer maximum number of objects to fetch from the server at a time
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> ownInstanceIterator(com.matisse.MtDatabase db, int numObjPerBuffer) {
        return getClass(db).<E>ownInstanceIterator(numObjPerBuffer, EmployeeLogin.class);
    }

    /**
     * Counts the number of own instances of this class (excluding subclasses).
     * @param db a database
     * @return total number of instances
     */
    public static long getOwnInstanceNumber(com.matisse.MtDatabase db) {
        return getClass(db).getOwnInstanceNumber();
    }

    /*
     * Attribute access methods
     */

    /* Attribute 'Username' */

    /** Attribute <code>Username</code> cache ID */
    private static int usernameCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtAttribute.Loader("Username",CID));

    /**
     * Gets the <code>Username</code> attribute descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db the database containing the attribute
     * @return the attribute descriptor object
     */
    public static com.matisse.reflect.MtAttribute getUsernameAttribute(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtAttribute)db.getCachedObject(usernameCID);
    }


    /**
     * Gets the <code>Username</code> attribute value.
     * @return the value
     *
     * @see #setUsername
     * @see #removeUsername
     */
    public final String getUsername() {
        return getString(getUsernameAttribute(getMtDatabase()));
    }

    /**
     * Sets the <code>Username</code> attribute value.
     * @param val the new value
     *
     * @see #getUsername
     * @see #removeUsername
     */
    public final void setUsername(String val) {
        setString(getUsernameAttribute(getMtDatabase()), val);
    }

    /**
     * Removes the current <code>Username</code> attribute value.
     * Falls back to the default value is there is one.  If the attribute
     * has no default value, it must be set to a new value before commit.
     *
     * @see #getUsername
     * @see #setUsername
     */
    public final void removeUsername() {
        removeValue(getUsernameAttribute(getMtDatabase()));
    }

    /**
     * Check if attribute value is set to its default value.
     * @return true if default value
     *
     * @see #getUsername
     * @see #setUsername
     */
    public final boolean isUsernameDefaultValue() {
        return isDefaultValue(getUsernameAttribute(getMtDatabase()));
    }


    /* Attribute 'Password' */

    /** Attribute <code>Password</code> cache ID */
    private static int passwordCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtAttribute.Loader("Password",CID));

    /**
     * Gets the <code>Password</code> attribute descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db the database containing the attribute
     * @return the attribute descriptor object
     */
    public static com.matisse.reflect.MtAttribute getPasswordAttribute(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtAttribute)db.getCachedObject(passwordCID);
    }


    /**
     * Gets the <code>Password</code> attribute value.
     * @return the value
     *
     * @see #setPassword
     * @see #removePassword
     */
    public final String getPassword() {
        return getString(getPasswordAttribute(getMtDatabase()));
    }

    /**
     * Sets the <code>Password</code> attribute value.
     * @param val the new value
     *
     * @see #getPassword
     * @see #removePassword
     */
    public final void setPassword(String val) {
        setString(getPasswordAttribute(getMtDatabase()), val);
    }

    /**
     * Removes the current <code>Password</code> attribute value.
     * Falls back to the default value is there is one.  If the attribute
     * has no default value, it must be set to a new value before commit.
     *
     * @see #getPassword
     * @see #setPassword
     */
    public final void removePassword() {
        removeValue(getPasswordAttribute(getMtDatabase()));
    }

    /**
     * Check if attribute value is set to its default value.
     * @return true if default value
     *
     * @see #getPassword
     * @see #setPassword
     */
    public final boolean isPasswordDefaultValue() {
        return isDefaultValue(getPasswordAttribute(getMtDatabase()));
    }


    /* Attribute 'LastLogin' */

    /** Attribute <code>LastLogin</code> cache ID */
    private static int lastLoginCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtAttribute.Loader("LastLogin",CID));

    /**
     * Gets the <code>LastLogin</code> attribute descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db the database containing the attribute
     * @return the attribute descriptor object
     */
    public static com.matisse.reflect.MtAttribute getLastLoginAttribute(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtAttribute)db.getCachedObject(lastLoginCID);
    }


    /**
     * Gets the <code>LastLogin</code> attribute value.
     * @return the value
     *
     * @see #setLastLogin
     * @see #removeLastLogin
     */
    public final java.util.GregorianCalendar getLastLogin() {
        return getDate(getLastLoginAttribute(getMtDatabase()));
    }

    /**
     * Sets the <code>LastLogin</code> attribute value.
     * @param val the new value
     *
     * @see #getLastLogin
     * @see #removeLastLogin
     */
    public final void setLastLogin(java.util.GregorianCalendar val) {
        setDate(getLastLoginAttribute(getMtDatabase()), val);
    }

    /**
     * Removes the current <code>LastLogin</code> attribute value.
     * Falls back to the default value is there is one.  If the attribute
     * has no default value, it must be set to a new value before commit.
     *
     * @see #getLastLogin
     * @see #setLastLogin
     */
    public final void removeLastLogin() {
        removeValue(getLastLoginAttribute(getMtDatabase()));
    }

    /**
     * Check if nullable attribute value is set to MT_NULL.
     * @return true if null value
     *
     * @see #getLastLogin
     * @see #setLastLogin
     */
    public final boolean isLastLoginNull() {
        return isNull(getLastLoginAttribute(getMtDatabase()));
    }

    /**
     * Check if attribute value is set to its default value.
     * @return true if default value
     *
     * @see #getLastLogin
     * @see #setLastLogin
     */
    public final boolean isLastLoginDefaultValue() {
        return isDefaultValue(getLastLoginAttribute(getMtDatabase()));
    }


    /*
     * Relationship access methods
     */

    /* Relationship 'Employee' */

    /** Relationship <code>Employee</code> cache ID */
    private static int employeeCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtRelationship.Loader("Employee",CID));

    /**
     * Gets the <code>Employee</code> relationship descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db a database
     * @return a relationship descriptor object
     */
    public static com.matisse.reflect.MtRelationship getEmployeeRelationship(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtRelationship)db.getCachedObject(employeeCID);
    }

    /**
     * Gets the <code>Employee</code> sucessor object.
     * @return an object 
     *
     * @see #setEmployee
     * @see #clearEmployee
     */
    public final com.joe.models.Employee getEmployee() {
        return (com.joe.models.Employee)getSuccessor(getEmployeeRelationship(getMtDatabase()));
    }

    /**
     * Sets the <code>Employee</code> successor object. It is not necessary to clear the
     * relationship before setting a new successor.
     * @param succ the new successor object
     *
     * @see #getEmployee
     * @see #clearEmployee
     */
    public final void setEmployee(com.joe.models.Employee succ) {
        setSuccessor(getEmployeeRelationship(getMtDatabase()), succ);
    }

    /**
     * Removes all <code>Employee</code> successors.  When the minimum cardinality
     * is 1, a new successor must be set before commit.
     */
    public final void clearEmployee() {
        clearSuccessors(getEmployeeRelationship(getMtDatabase()));
    }


    /*
     * Index access methods
     */

    /* Index 'Username_IDX' */

    /** Index <code>Username_IDX</code> cache ID */
    private static int username_IDXIndexCID = com.matisse.MtDatabase.allocCID(new com.matisse.reflect.MtIndex.Loader("com.joe.models.Username_IDX"));

    /**
     * Gets the <code>Username_IDX</code> index descriptor.
     * This method supports advanced MATISSE programming techniques such as
     * dynamically modifying the schema.
     * @param db a database
     * @return an index descriptor object
     */
    public static com.matisse.reflect.MtIndex getUsername_IDXIndex(com.matisse.MtDatabase db) {
        return (com.matisse.reflect.MtIndex)db.getCachedObject(username_IDXIndexCID);
    }

    /**
     * Finds one <code>EmployeeLogin</code> object in index <code>Username_IDX</code>.
     * @param db a database
     * @param username search parameter
     * @return the matching <code>EmployeeLogin</code> object or <code>null</code> if none was found
     */
    public static EmployeeLogin lookupUsername_IDX(com.matisse.MtDatabase db, String username) {
        return (EmployeeLogin)getUsername_IDXIndex(db).lookup(new Object[] {username}, getClass(db));
    }

    /**
     * Finds <code>EmployeeLogin</code> objects in index <code>Username_IDX</code>.
     * @param db a database
     * @param username search parameter
     * @return the matching <code>EmployeeLogin</code> objects or an empty array if none was found
     */
    public static EmployeeLogin[] lookupObjectsUsername_IDX(com.matisse.MtDatabase db, String username) {
        return (EmployeeLogin[])getUsername_IDXIndex(db).lookupObjects(new Object[] {username}, getClass(db), EmployeeLogin.class);
    }

    /**
     * Opens an iterator on index <code>Username_IDX</code> for class <code>EmployeeLogin</code>.
     * Each indexed attribute has a pair of "from" and "to" parameters which
     * define the search range for that attribute.  With strings, you may use
     * <code>null</code> to start "from" at the beginning or extend "to" to the end.
     * @param <E> a MtObject class     * @param db a database
     * @param fromUsername search parameter
     * @param toUsername search parameter
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> username_IDXIterator(com.matisse.MtDatabase db, String fromUsername, String toUsername) {
        return getUsername_IDXIndex(db).<E>iterator(new Object[] {fromUsername}, new Object[] {toUsername}, getClass(db), com.matisse.reflect.MtIndex.DIRECT, com.matisse.MtDatabase.MAX_PREFETCHING, EmployeeLogin.class);
    }

    /**
     * Opens an iterator on index <code>Username_IDX</code> for class <code>EmployeeLogin</code>. 
     * Each indexed attribute has a pair of "from" and "to" parameters which
     * define the search range for that attribute.  With strings, you may use
     * <code>null</code> to start "from" at the beginning or extend "to" to the end.
     * @param <E> a MtObject class     * @param db a database
     * @param fromUsername search parameter
     * @param toUsername search parameter
     * @param filterClass a subclass; use <code>null</code> not to restrict iterator to a subclass
     * @param direction MtIndex.DIRECT or MtIndex.REVERSE
     * @param numObjPerBuffer maximum number of objects to be retrieved in each request to server
     * @return an object iterator
     */
    public static <E extends com.matisse.reflect.MtObject> com.matisse.MtObjectIterator<E> username_IDXIterator(com.matisse.MtDatabase db, String fromUsername, String toUsername, com.matisse.reflect.MtClass filterClass, int direction, int numObjPerBuffer) {
        return getUsername_IDXIndex(db).iterator(new Object[] {fromUsername}, new Object[] {toUsername}, filterClass, direction, numObjPerBuffer, EmployeeLogin.class);
    }

    // END of Matisse SDL Generated Code

    /*
     * You may add your own code here...
     */

    /**
     * Default constructor provided as an example.
     * You may delete this constructor or modify it to suit your needs. If you
     * modify it, please revise this comment accordingly.
     * @param db a database
     */
    public EmployeeLogin(com.matisse.MtDatabase db) {
        super(getClass(db));
    }

    /**
     * Example of <code>toString</code> method.
     * You may delete this method or modify it to suit your needs. If you
     * modify it, please revise this comment accordingly.
     * @return a string
     */
    public java.lang.String toString() {
        return super.toString() + "[EmployeeLogin]";
    }
}
