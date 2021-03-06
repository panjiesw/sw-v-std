/**
 * This class is generated by jOOQ
 */
package com.panjiesw.std.service.user.sql.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StdRole extends org.jooq.impl.TableImpl<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord> {

	private static final long serialVersionUID = 1916706922;

	/**
	 * The singleton instance of <code>public.std_role</code>
	 */
	public static final com.panjiesw.std.service.user.sql.tables.StdRole STD_ROLE = new com.panjiesw.std.service.user.sql.tables.StdRole();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord> getRecordType() {
		return com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord.class;
	}

	/**
	 * The column <code>public.std_role.id</code>.
	 */
	public final org.jooq.TableField<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.std_role.name</code>.
	 */
	public final org.jooq.TableField<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(16).nullable(false), this, "");

	/**
	 * The column <code>public.std_role.description</code>.
	 */
	public final org.jooq.TableField<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>public.std_role</code> table reference
	 */
	public StdRole() {
		this("std_role", null);
	}

	/**
	 * Create an aliased <code>public.std_role</code> table reference
	 */
	public StdRole(java.lang.String alias) {
		this(alias, com.panjiesw.std.service.user.sql.tables.StdRole.STD_ROLE);
	}

	private StdRole(java.lang.String alias, org.jooq.Table<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord> aliased) {
		this(alias, aliased, null);
	}

	private StdRole(java.lang.String alias, org.jooq.Table<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.panjiesw.std.service.user.sql.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord, java.lang.Long> getIdentity() {
		return com.panjiesw.std.service.user.sql.Keys.IDENTITY_STD_ROLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord> getPrimaryKey() {
		return com.panjiesw.std.service.user.sql.Keys.STD_ROLE_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.panjiesw.std.service.user.sql.tables.records.StdRoleRecord>>asList(com.panjiesw.std.service.user.sql.Keys.STD_ROLE_PKEY, com.panjiesw.std.service.user.sql.Keys.UNIQUE_NAME);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.panjiesw.std.service.user.sql.tables.StdRole as(java.lang.String alias) {
		return new com.panjiesw.std.service.user.sql.tables.StdRole(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.panjiesw.std.service.user.sql.tables.StdRole rename(java.lang.String name) {
		return new com.panjiesw.std.service.user.sql.tables.StdRole(name, null);
	}
}
