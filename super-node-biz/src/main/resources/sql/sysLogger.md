

all
===
    select #use("cols")# from sys_logger where #use("condition")# #use("orderBy")#

sample
===
	select #use("cols")# from sys_logger sys_logger where  #use("condition")#

cols
===
	sys_logger.id,sys_logger.clientIp,sys_logger.createTime,sys_logger.deleted,sys_logger.httpStatusCode,sys_logger.method,sys_logger.paramData,sys_logger.returnData,sys_logger.returnTime,sys_logger.sessionId,sys_logger.time,sys_logger.timeConsuming,sys_logger.type,sys_logger.updateTime,sys_logger.uri,sys_logger.weight,sys_logger.json,sys_logger.version,sys_logger.create_user,sys_logger.update_user
updateSample
===
	sys_logger.id=#id#,sys_logger.clientIp=#clientip#,sys_logger.createTime=#createtime#,sys_logger.deleted=#deleted#,sys_logger.httpStatusCode=#httpstatuscode#,sys_logger.method=#method#,sys_logger.paramData=#paramdata#,sys_logger.returnData=#returndata#,sys_logger.returnTime=#returntime#,sys_logger.sessionId=#sessionid#,sys_logger.time=#time#,sys_logger.timeConsuming=#timeconsuming#,sys_logger.type=#type#,sys_logger.updateTime=#updatetime#,sys_logger.uri=#uri#,sys_logger.weight=#weight#,sys_logger.json=#json#,sys_logger.version=#version#,sys_logger.create_user=#createUser#,sys_logger.update_user=#updateUser#

forceDeleteByIds
===
    delete from sys_logger where sys_logger.id in  (#join(ids)#)

deleteByIds
===
    delete from sys_logger where sys_logger.id in (#join(ids)#)

findByIds
===
    select #use("cols")# from sys_logger where sys_logger.id in (#join(ids)#)

condition
===
    sys_logger.deleted = 0
	@if(!isEmpty(id)){
	 and sys_logger.id=#id#
	@}
	@if(!isEmpty(clientip)){
	 and sys_logger.clientIp=#clientip#
	@}
	@if(!isEmpty(createtime)){
	 and sys_logger.createTime=#createtime#
	@}
	@if(!isEmpty(deleted)){
	 and sys_logger.deleted=#deleted#
	@}
	@if(!isEmpty(httpstatuscode)){
	 and sys_logger.httpStatusCode=#httpstatuscode#
	@}
	@if(!isEmpty(method)){
	 and sys_logger.method=#method#
	@}
	@if(!isEmpty(paramdata)){
	 and sys_logger.paramData=#paramdata#
	@}
	@if(!isEmpty(returndata)){
	 and sys_logger.returnData=#returndata#
	@}
	@if(!isEmpty(returntime)){
	 and sys_logger.returnTime=#returntime#
	@}
	@if(!isEmpty(sessionid)){
	 and sys_logger.sessionId=#sessionid#
	@}
	@if(!isEmpty(time)){
	 and sys_logger.time=#time#
	@}
	@if(!isEmpty(timeconsuming)){
	 and sys_logger.timeConsuming=#timeconsuming#
	@}
	@if(!isEmpty(type)){
	 and sys_logger.type=#type#
	@}
	@if(!isEmpty(updatetime)){
	 and sys_logger.updateTime=#updatetime#
	@}
	@if(!isEmpty(uri)){
	 and sys_logger.uri=#uri#
	@}
	@if(!isEmpty(weight)){
	 and sys_logger.weight=#weight#
	@}

orderBy
===
	order by sys_logger.create_time desc

groupById
===
    group by sys_logger.id
