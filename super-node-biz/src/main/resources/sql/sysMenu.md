

all
===
    select #use("cols")# from sys_menu where #use("condition")# #use("orderBy")#

sample
===
	select #use("cols")# from sys_menu sys_menu where  #use("condition")#

cols
===
	sys_menu.id,sys_menu.create_time,sys_menu.update_time,sys_menu.creator,sys_menu.deleted,sys_menu.display,sys_menu.flag,sys_menu.icon,sys_menu.menu_type,sys_menu.name,sys_menu.permission,sys_menu.url,sys_menu.weight,sys_menu.parent_id,sys_menu.json,sys_menu.version,sys_menu.create_user,sys_menu.update_user
updateSample
===
	sys_menu.id=#id#,sys_menu.create_time=#createTime#,sys_menu.update_time=#updateTime#,sys_menu.creator=#creator#,sys_menu.deleted=#deleted#,sys_menu.display=#display#,sys_menu.flag=#flag#,sys_menu.icon=#icon#,sys_menu.menu_type=#menuType#,sys_menu.name=#name#,sys_menu.permission=#permission#,sys_menu.url=#url#,sys_menu.weight=#weight#,sys_menu.parent_id=#parentId#,sys_menu.json=#json#,sys_menu.version=#version#,sys_menu.create_user=#createUser#,sys_menu.update_user=#updateUser#

forceDeleteByIds
===
    delete from sys_menu where sys_menu.id in  (#join(ids)#)

deleteByIds
===
    delete from sys_menu where sys_menu.id in (#join(ids)#)

findByIds
===
    select #use("cols")# from sys_menu where sys_menu.id in (#join(ids)#)

findMenus
===
     select #use("cols")# from sys_menu where #use("condition")#  #use("orderBy")#
     
condition
===
    sys_menu.deleted = 0
	@if(!isEmpty(id)){
	 and sys_menu.id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and sys_menu.create_time=#createTime#
	@}
	@if(!isEmpty(createUser)){
	 and sys_menu.create_user=#createUser#
	@}
	@if(!isEmpty(deleted)){
	 and sys_menu.deleted=#deleted#
	@}
	@if(!isEmpty(display)){
	 and sys_menu.display=#display#
	@}
	@if(!isEmpty(flag)){
	 and sys_menu.flag=#flag#
	@}
	@if(!isEmpty(icon)){
	 and sys_menu.icon=#icon#
	@}
	@if(!isEmpty(menuType)){
	 and sys_menu.menu_type=#menuType#
	@}
	@if(!isEmpty(name)){
	 and sys_menu.name=#name#
	@}
	@if(!isEmpty(permission)){
	 and sys_menu.permission=#permission#
	@}
	@if(!isEmpty(updateTime)){
	 and sys_menu.update_time=#updateTime#
	@}
	@if(!isEmpty(updateUser)){
	 and sys_menu.update_user=#updateUser#
	@}
	@if(!isEmpty(url)){
	 and sys_menu.url=#url#
	@}
	@if(!isEmpty(weight)){
	 and sys_menu.weight=#weight#
	@}
	@if(!isEmpty(parentId)){
	 and sys_menu.parent_id=#parentId#
	@}

orderBy
===
	order by sys_menu.create_time desc

groupById
===
    group by sys_menu.id



findMenuByUserId
===
    SELECT DISTINCT
        m.*
    FROM
        sys_menu m,
        sys_user u,
        sys_role_menu rm,
        sys_user_role ur
    WHERE
        m.id = rm.menu_id
    AND ur.role_id = rm.role_id
    AND ur.user_id = u.id
    AND u.id=#userId#
     and u.deleted = 0 and rm.deleted = 0 and ur.deleted =0 

findMenuByRoleId
===

    
    SELECT DISTINCT
        m.*
    FROM
    sys_role r,
        sys_menu m,
        sys_role_menu rm
    WHERE
    rm.menu_id=m.id
    AND 
    r.id=#roleId#
    r.deleted = 0 AND m.deleted =0 AND rm.deleted = 0
