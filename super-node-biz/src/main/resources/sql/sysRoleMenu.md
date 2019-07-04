

all
===
    select #use("cols")# from sys_role_menu where #use("condition")# #use("orderBy")#

sample
===
	select #use("cols")# from sys_role_menu sys_role_menu where  #use("condition")#

cols
===
	sys_role_menu.id,sys_role_menu.create_time,sys_role_menu.update_time,sys_role_menu.deleted,sys_role_menu.flag,sys_role_menu.weight,sys_role_menu.menu_id,sys_role_menu.role_id,sys_role_menu.json,sys_role_menu.version,sys_role_menu.create_user,sys_role_menu.update_user
updateSample
===
	sys_role_menu.id=#id#,sys_role_menu.create_time=#createTime#,sys_role_menu.update_time=#updateTime#,sys_role_menu.deleted=#deleted#,sys_role_menu.flag=#flag#,sys_role_menu.weight=#weight#,sys_role_menu.menu_id=#menuId#,sys_role_menu.role_id=#roleId#,sys_role_menu.json=#json#,sys_role_menu.version=#version#,sys_role_menu.create_user=#createUser#,sys_role_menu.update_user=#updateUser#

forceDeleteByIds
===
    delete from sys_role_menu where sys_role_menu.id in  (#join(ids)#)

deleteByIds
===
    delete from sys_role_menu where sys_role_menu.id in (#join(ids)#)

findByIds
===
    select #use("cols")# from sys_role_menu where sys_role_menu.id in (#join(ids)#)

deleteByTemplate
===
    delete from sys_role_menu where #use("condition")#
    
condition
===
    sys_role_menu.deleted = 0
	@if(!isEmpty(id)){
	 and sys_role_menu.id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and sys_role_menu.create_time=#createTime#
	@}
	@if(!isEmpty(createUser)){
	 and sys_role_menu.create_user=#createUser#
	@}
	@if(!isEmpty(deleted)){
	 and sys_role_menu.deleted=#deleted#
	@}
	@if(!isEmpty(flag)){
	 and sys_role_menu.flag=#flag#
	@}
	@if(!isEmpty(updateTime)){
	 and sys_role_menu.update_time=#updateTime#
	@}
	@if(!isEmpty(weight)){
	 and sys_role_menu.weight=#weight#
	@}
	@if(!isEmpty(menuId)){
	 and sys_role_menu.menu_id=#menuId#
	@}
	@if(!isEmpty(roleId)){
	 and sys_role_menu.role_id=#roleId#
	@}

orderBy
===
	order by sys_role_menu.create_time desc

groupById
===
    group by sys_role_menu.id


deleteSample
===
    
    
    delete from sys_role_menu where role_id=#roleId#

