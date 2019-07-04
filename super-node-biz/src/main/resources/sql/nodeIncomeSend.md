
all
===
    select node_income_send.#use("cols")# from node_income_send where node_income_send.deleted = 0

sample
===
	select #use("cols")# from node_income_send node_income_send where  #use("condition")# #use("orderBy")#

cols
===
	node_income_send.id,node_income_send.receive_address,node_income_send.send_address,node_income_send.send_value,node_income_send.txid,node_income_send.block_height_start,node_income_send.block_height_end,node_income_send.create_time,node_income_send.update_time,node_income_send.deleted,node_income_send.weight,node_income_send.json,node_income_send.version
updateSample
===
	node_income_send.id=#id#,node_income_send.receive_address=#receiveAddress#,node_income_send.send_address=#sendAddress#,node_income_send.send_value=#sendValue#,node_income_send.txid=#txid#,node_income_send.block_height_start=#blockHeightStart#,node_income_send.block_height_end=#blockHeightEnd#,node_income_send.create_time=#createTime#,node_income_send.update_time=#updateTime#,node_income_send.deleted=#deleted#,node_income_send.weight=#weight#,node_income_send.json=#json#,node_income_send.version=#version#
forceDeleteByIds
===
    delete from node_income_send where node_income_send.id in (#ids#)

deleteByIds
===
    update node_income_send set node_income_send.deleted = 1 where node_income_send.id in (#{ids})

templatePage
===
     SELECT @pageTag(){
               	#use("cols")#
           @} 
     from node_income_send
     where  #use("range_condition")# 	
     #use("orderBy")# 

range_condition
===
    node_income_send.deleted = 0
	@if(!isEmpty(id)){
	 and node_income_send.id=#id#
	@}
	@if(!isEmpty(receiveAddress)){
	 and node_income_send.receive_address=#receiveAddress#
	@}
	@if(!isEmpty(sendAddress)){
	 and node_income_send.send_address=#sendAddress#
	@}
	@if(!isEmpty(sendValue)){
	 and node_income_send.send_value=#sendValue#
	@}
	@if(!isEmpty(txid)){
	 and node_income_send.txid=#txid#
	@}
	@if(!isEmpty(blockHeightStart)){
	 and node_income_send.block_height_start >= #blockHeightStart#
	@}
	@if(!isEmpty(blockHeightEnd)){
	 and node_income_send.block_height_end <= #blockHeightEnd#
	@}
	@if(!isEmpty(searchTimeStartDate)){
	 and node_income_send.create_time>=#searchTimeStartDate#
	@}
	@if(!isEmpty(searchTimeEndDate)){
	 and node_income_send.create_time<=#searchTimeEndDate#
	@}
	@if(!isEmpty(updateTime)){
	 and node_income_send.update_time=#searchTimeStartDate#
	@}
	@if(!isEmpty(deleted)){
	 and node_income_send.deleted=#deleted#
	@}
	@if(!isEmpty(weight)){
	 and node_income_send.weight=#weight#
	@}
	@if(!isEmpty(json)){
	 and node_income_send.json=#json#
	@}
	@if(!isEmpty(version)){
	 and node_income_send.version=#version#
	@}

condition
===
    node_income_send.deleted = 0
	@if(!isEmpty(id)){
	 and node_income_send.id=#id#
	@}
	@if(!isEmpty(receiveAddress)){
	 and node_income_send.receive_address=#receiveAddress#
	@}
	@if(!isEmpty(sendAddress)){
	 and node_income_send.send_address=#sendAddress#
	@}
	@if(!isEmpty(sendValue)){
	 and node_income_send.send_value=#sendValue#
	@}
	@if(!isEmpty(txid)){
	 and node_income_send.txid=#txid#
	@}
	@if(!isEmpty(blockHeightStart)){
	 and node_income_send.block_height_start=#blockHeightStart#
	@}
	@if(!isEmpty(blockHeightEnd)){
	 and node_income_send.block_height_end=#blockHeightEnd#
	@}
	@if(!isEmpty(createTime)){
	 and node_income_send.create_time=#createTime#
	@}
	@if(!isEmpty(updateTime)){
	 and node_income_send.update_time=#updateTime#
	@}
	@if(!isEmpty(deleted)){
	 and node_income_send.deleted=#deleted#
	@}
	@if(!isEmpty(weight)){
	 and node_income_send.weight=#weight#
	@}
	@if(!isEmpty(json)){
	 and node_income_send.json=#json#
	@}
	@if(!isEmpty(version)){
	 and node_income_send.version=#version#
	@}

orderBy
===
	order by node_income_send.create_time desc

groupById
===
    group by node_income_send.id
    
    
queryNewHeight
===
    select node_income_send.block_height_end 
    from node_income_send 
    where node_income_send.deleted = 0 
    order by node_income_send.block_height_end desc
    limit 0 ,1
    
querySumValueByAddressAndHeight
===
    select sum(node_income_send.send_value)
    from node_income_send
    where node_income_send.deleted = 0 
    @if(!isEmpty(startHeight)){
        and node_income_send.block_height_start >= #startHeight#
    @}
    @if(!isEmpty(endHeight)){
        and  #endHeight# >= node_income_send.block_height
    @}
    @if(!isEmpty(address)){
         and  #address# = node_income_send.receive_address
    @}
    