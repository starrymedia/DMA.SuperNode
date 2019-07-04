

all
===
    select #use("cols")# from  where #use("condition")# #use("orderBy")#

findByUserName
===
	select #use("cols")# from sys_user  where  #use("condition")#

cols
===
	sys_user.id,sys_user.create_time,sys_user.update_time,sys_user.flag,sys_user.deleted,sys_user.login_time,sys_user.name,sys_user.password,sys_user.json,sys_user.version,sys_user.weight,sys_user.create_user,sys_user.update_user
updateSample
===
	sys_user.id=#id#,sys_user.create_time=#createTime#,sys_user.update_time=#updateTime#,sys_user.flag=#flag#,sys_user.deleted=#deleted#,sys_user.login_time=#loginTime#,sys_user.name=#name#,sys_user.password=#password#,sys_user.json=#json#,sys_user.version=#version#,sys_user.weight=#weight#,sys_user.create_user=#createUser#,sys_user.update_user=#updateUser#

forceDeleteByIds
===
    delete from sys_user where sys_user.id in  (#join(ids)#)

deleteByIds
===
    delete from sys_user where sys_user.id in (#join(ids)#)

findByIds
===
    select #use("cols")# from sys_user where sys_user.id in (#join(ids)#)

condition
===
    sys_user.deleted = 0
	@if(!isEmpty(id)){
	 and sys_user.id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and sys_user.create_time=#createTime#
	@}
	@if(!isEmpty(createUser)){
	 and sys_user.create_user=#createUser#
	@}
	@if(!isEmpty(email)){
	 and sys_user.email=#email#
	@}
	@if(!isEmpty(flag)){
	 and sys_user.flag=#flag#
	@}
	@if(!isEmpty(loginTime)){
	 and sys_user.login_time=#loginTime#
	@}
	@if(!isEmpty(name)){
	 and sys_user.name=#name#
	@}
	@if(!isEmpty(password)){
	 and sys_user.`password`=#password#
	@}
	@if(!isEmpty(storeId)){
	 and sys_user.store_id=#store_id#
	@}
	@if(!isEmpty(updateTime)){
	 and sys_user.update_time=#updateTime#
	@}
	@if(!isEmpty(updateUser)){
	 and sys_user.update_user=#updateUser#
	@}

orderBy
===
	order by sys_user.create_time desc

groupById
===
    group by sys_user.id
