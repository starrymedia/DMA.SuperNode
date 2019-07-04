
all
===
    select sys_config.#use("cols")# from sys_config where sys_config.deleted = 0

sample
===
	select #use("cols")# from sys_config sys_config where  #use("condition")# #use("orderBy")#

cols
===
	sys_config.id,sys_config.config_key,sys_config.config_value,sys_config.create_time,sys_config.update_time,sys_config.deleted,sys_config.weight,sys_config.json,sys_config.version
updateSample
===
	sys_config.id=#id#,sys_config.config_key=#configKey#,sys_config.config_value=#configValue#,sys_config.create_time=#createTime#,sys_config.update_time=#updateTime#,sys_config.deleted=#deleted#,sys_config.weight=#weight#,sys_config.json=#json#,sys_config.version=#version#
forceDeleteByIds
===
    delete from sys_config where sys_config.id in (#ids#)

deleteByIds
===
    update sys_config set sys_config.deleted = 1 where sys_config.id in (#{ids})

condition
===
    sys_config.deleted = 0
	@if(!isEmpty(id)){
	 and sys_config.id=#id#
	@}
	@if(!isEmpty(configKey)){
	 and sys_config.config_key=#configKey#
	@}
	@if(!isEmpty(configValue)){
	 and sys_config.config_value=#configValue#
	@}
	@if(!isEmpty(createTime)){
	 and sys_config.create_time=#createTime#
	@}
	@if(!isEmpty(updateTime)){
	 and sys_config.update_time=#updateTime#
	@}
	@if(!isEmpty(deleted)){
	 and sys_config.deleted=#deleted#
	@}
	@if(!isEmpty(weight)){
	 and sys_config.weight=#weight#
	@}
	@if(!isEmpty(json)){
	 and sys_config.json=#json#
	@}
	@if(!isEmpty(version)){
	 and sys_config.version=#version#
	@}

orderBy
===
	order by sys_config.create_time desc

groupById
===
    group by sys_config.id
