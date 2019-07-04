

all
===
    select #use("cols")# from sys_button where #use("condition")# #use("orderBy")#

sample
===
	select #use("cols")# from  sys_button where  #use("condition")#

cols
===
	sys_button.id,sys_button.deleted,sys_button.menu_id,sys_button.name,sys_button.permission,sys_button.weight,sys_button.json,sys_button.version,sys_button.create_time,sys_button.update_time,sys_button.create_user,sys_button.update_user
updateSample
===
	sys_button.id=#id#,sys_button.deleted=#deleted#,sys_button.menu_id=#menuId#,sys_button.name=#name#,sys_button.permission=#permission#,sys_button.weight=#weight#,sys_button.json=#json#,sys_button.version=#version#,sys_button.create_time=#createTime#,sys_button.update_time=#updateTime#,sys_button.create_user=#createUser#,sys_button.update_user=#updateUser#

forceDeleteByIds
===
    delete from sys_button where sys_button.id in  (#join(ids)#)

deleteByIds
===
    delete from sys_button where sys_button.id in (#join(ids)#)

findByIds
===
    select #use("cols")# from sys_button where sys_button.id in (#join(ids)#)

condition
===
    sys_button.deleted = 0
	@if(!isEmpty(id)){
	 and sys_button.id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and sys_button.create_time=#createTime#
	@}
	@if(!isEmpty(deleted)){
	 and sys_button.deleted=#deleted#
	@}
	@if(!isEmpty(menuId)){
	 and sys_button.menu_id=#menuId#
	@}
	@if(!isEmpty(name)){
	 and sys_button.name=#name#
	@}
	@if(!isEmpty(permission)){
	 and sys_button.permission=#permission#
	@}
	@if(!isEmpty(updateTime)){
	 and sys_button.update_time=#updateTime#
	@}
	@if(!isEmpty(weight)){
	 and sys_button.weight=#weight#
	@}

orderBy
===
	order by sys_button.create_time desc

groupById
===
    group by sys_button.id

findButtonByRoleId
===
    SELECT DISTINCT b.*
    FROM
    sys_role r,
        sys_button b,
        sys_role_button rb
    WHERE
    rb.button_id=b.id
    AND 
    rb.role_id=r.id
    AND 
    r.id=#roleId#