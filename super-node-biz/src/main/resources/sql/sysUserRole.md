

all
===
    select #use("cols")# from sys_user_role where #use("condition")# #use("orderBy")#

sample
===
	select #use("cols")# from sys_user_role sys_user_role where  #use("condition")#

cols
===
	sys_user_role.id,sys_user_role.create_time,sys_user_role.update_time,sys_user_role.deleted,sys_user_role.weight,sys_user_role.button_id,sys_user_role.user_id,sys_user_role.role_id,sys_user_role.json,sys_user_role.version,sys_user_role.create_user,sys_user_role.update_user

updateSample
===
	sys_user_role.id=#id#,sys_user_role.create_time=#createTime#,sys_user_role.update_time=#updateTime#,sys_user_role.deleted=#deleted#,sys_user_role.weight=#weight#,sys_user_role.button_id=#buttonId#,sys_user_role.user_id=#userId#,sys_user_role.role_id=#roleId#,sys_user_role.json=#json#,sys_user_role.version=#version#,sys_user_role.create_user=#createUser#,sys_user_role.update_user=#updateUser#

forceDeleteByIds
===
    delete from sys_user_role where sys_user_role.id in  (#join(ids)#)

deleteByIds
===
    delete from sys_user_role where sys_user_role.id in (#join(ids)#)

findByIds
===
    select #use("cols")# from sys_user_role where sys_user_role.id in (#join(ids)#)

condition
===
    sys_user_role.deleted = 0
	@if(!isEmpty(id)){
	 and sys_user_role.id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and sys_user_role.create_time=#createTime#
	@}
	@if(!isEmpty(createUser)){
	 and sys_user_role.create_user=#createUser#
	@}
	@if(!isEmpty(deleted)){
	 and sys_user_role.deleted=#deleted#
	@}
	@if(!isEmpty(updateTime)){
	 and sys_user_role.update_time=#updateTime#
	@}
	@if(!isEmpty(weight)){
	 and sys_user_role.weight=#weight#
	@}
	@if(!isEmpty(userId)){
	 and sys_user_role.user_id=#userId#
	@}
	@if(!isEmpty(roleId)){
	 and sys_user_role.role_id=#roleId#
	@}

orderBy
===
	order by sys_user_role.create_time desc

groupById
===
    group by sys_user_role.id



deleteByRoleIds
===
    delete from sys_user_role where sys_user_role.role_id in (#join(roleIds)#)

findByRoleIds
===
    select  #use("cols")# from sys_user_role where sys_user_role.role_id in (#join(roleIds)#)

deleteByUserId
===
    
    delete from sys_user_role where user_id=#userId#

findByUserId
===
     select #use("cols")# from sys_user_role where #use("condition")# #use("orderBy")#

