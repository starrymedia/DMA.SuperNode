

all
===
    select #use("cols")# from sys_role where #use("condition")# #use("orderBy")#

sample
===
	select #use("cols")# from sys_role sys_role where  #use("condition")#

cols
===
	sys_role.id,sys_role.create_time,sys_role.update_time,sys_role.deleted,sys_role.description,sys_role.name,sys_role.weight,sys_role.json,sys_role.version,sys_role.create_user,sys_role.update_user,sys_role.store_id,sys_role.role_type
updateSample
===
	sys_role.id=#id#,sys_role.create_time=#createTime#,sys_role.update_time=#updateTime#,sys_role.deleted=#deleted#,sys_role.description=#description#,sys_role.name=#name#,sys_role.weight=#weight#,sys_role.json=#json#,sys_role.version=#version#,sys_role.create_user=#createUser#,sys_role.update_user=#updateUser#,sys_role.store_id=#storeId#,sys_role.role_type=#roleType#

forceDeleteByIds
===
    delete from sys_role where sys_role.id in  (#join(ids)#)

deleteByIds
===
    delete from sys_role where sys_role.id in (#join(ids)#)

findByIds
===
    select #use("cols")# from sys_role where sys_role.id in (#join(ids)#)


findRoleByUserId
===
    select r.* FROM sys_user u,sys_role r,sys_user_role ur WHERE u.id=ur.user_id AND r.id=ur.role_id AND u.id=#userId#
    
    
findStoreSuperRole
===
    
    
    
condition
===
    sys_role.deleted = 0
	@if(!isEmpty(id)){
	 and sys_role.id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and sys_role.create_time=#createTime#
	@}
	@if(!isEmpty(createUser)){
	 and sys_role.create_user=#createUser#
	@}
	@if(!isEmpty(deleted)){
	 and sys_role.deleted=#deleted#
	@}
	@if(!isEmpty(description)){
	 and sys_role.description=#description#
	@}
	@if(!isEmpty(name)){
	 and sys_role.name=#name#
	@}
	@if(!isEmpty(updateTime)){
	 and sys_role.update_time=#updateTime#
	@}
	@if(!isEmpty(updateUser)){
	 and sys_role.update_user=#updateUser#
	@}
	@if(!isEmpty(weight)){
	 and sys_role.weight=#weight#
	@}
	@if(!isEmpty(roleId)){
	 and sys_role.role_id=#roleId#
	@}
	@if(!isEmpty(storeId)){
	 and sys_role.store_id=#storeId#
	@}

orderBy
===
	order by sys_role.create_time desc

groupById
===
    group by sys_role.id


findRoleByUserId
===
    select r.* FROM sys_user u,sys_role r,sys_user_role ur WHERE u.id=ur.user_id AND r.id=ur.role_id AND u.id=#userId#
    
	