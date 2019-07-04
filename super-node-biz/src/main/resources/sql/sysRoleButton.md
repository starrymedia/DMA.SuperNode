

all
===
    select #use("cols")# from sys_role_button where #use("condition")# #use("orderBy")#

sample
===
	select #use("cols")# from sys_role_button sys_role_button where  #use("condition")#

cols
===
	sys_role_button.id,sys_role_button.create_time,sys_role_button.update_time,sys_role_button.deleted,sys_role_button.flag,sys_role_button.weight,sys_role_button.button_id,sys_role_button.role_id,sys_role_button.json,sys_role_button.version,sys_role_button.create_user,sys_role_button.update_user
updateSample
===
	sys_role_button.id=#id#,sys_role_button.create_time=#createTime#,sys_role_button.update_time=#updateTime#,sys_role_button.deleted=#deleted#,sys_role_button.flag=#flag#,sys_role_button.weight=#weight#,sys_role_button.button_id=#buttonId#,sys_role_button.role_id=#roleId#,sys_role_button.json=#json#,sys_role_button.version=#version#,sys_role_button.create_user=#createUser#,sys_role_button.update_user=#updateUser#

forceDeleteByIds
===
    delete from sys_role_button where sys_role_button.id in  (#join(ids)#)

deleteByIds
===
    delete from sys_role_button where sys_role_button.id in (#join(ids)#)

findByIds
===
    select #use("cols")# from sys_role_button where sys_role_button.id in (#join(ids)#)
    
deleteByRoleId
===
    delete from sys_role_button where sys_role_button.role_id =#roleId#

condition
===
    sys_role_button.deleted = 0
	@if(!isEmpty(id)){
	 and sys_role_button.id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and sys_role_button.create_time=#createTime#
	@}
	@if(!isEmpty(createUser)){
	 and sys_role_button.create_user=#createUser#
	@}
	@if(!isEmpty(deleted)){
	 and sys_role_button.deleted=#deleted#
	@}
	@if(!isEmpty(flag)){
	 and sys_role_button.flag=#flag#
	@}
	@if(!isEmpty(updateTime)){
	 and sys_role_button.update_time=#updateTime#
	@}
	@if(!isEmpty(updateUser)){
	 and sys_role_button.update_user=#updateUser#
	@}
	@if(!isEmpty(weight)){
	 and sys_role_button.weight=#weight#
	@}
	@if(!isEmpty(buttonId)){
	 and sys_role_button.button_id=#buttonId#
	@}
	@if(!isEmpty(roleId)){
	 and sys_role_button.role_id=#roleId#
	@}

orderBy
===
	order by sys_role_button.create_time desc

groupById
===
    group by sys_role_button.id
