
all
===
    select voters.#use("cols")# from voters where voters.deleted = 0

sample
===
	select #use("cols")# from voters voters where  #use("condition")# #use("orderBy")#

cols
===
	voters.id,voters.create_time,voters.update_time,voters.deleted,voters.weight,voters.json,voters.version,voters.create_user,voters.update_user,voters.address,voters.txid,voters.block_height,voters.vote_time,voters.vote_height,voters.vote_value,voters.total_value
updateSample
===
	voters.id=#id#,voters.create_time=#createTime#,voters.update_time=#updateTime#,voters.deleted=#deleted#,voters.weight=#weight#,voters.json=#json#,voters.version=#version#,voters.create_user=#createUser#,voters.update_user=#updateUser#,voters.address=#address#,voters.txid=#txid#,voters.block_height=#blockHeight#,voters.vote_time=#voteTime#,voters.vote_height=#voteHeight#,voters.vote_value=#voteValue#,voters.total_value=#totalValue#

forceDeleteByIds
===
    delete from voters where voters.id in (#ids#)

deleteByIds
===
    update voters set voters.deleted = 1 where voters.id in (#{ids})

condition
===
    voters.deleted = 0
	@if(!isEmpty(id)){
	 and voters.id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and voters.create_time=#createTime#
	@}
	@if(!isEmpty(updateTime)){
	 and voters.update_time=#updateTime#
	@}
	@if(!isEmpty(deleted)){
	 and voters.deleted=#deleted#
	@}
	@if(!isEmpty(weight)){
	 and voters.weight=#weight#
	@}
	@if(!isEmpty(json)){
	 and voters.json=#json#
	@}
	@if(!isEmpty(version)){
	 and voters.version=#version#
	@}
	@if(!isEmpty(createUser)){
	 and voters.create_user=#createUser#
	@}
	@if(!isEmpty(updateUser)){
	 and voters.update_user=#updateUser#
	@}
	@if(!isEmpty(address)){
	 and voters.address=#address#
	@}
	@if(!isEmpty(txid)){
	 and voters.txid=#txid#
	@}
	@if(!isEmpty(blockHeight)){
	 and voters.block_height=#blockHeight#
	@}
	@if(!isEmpty(voteTime)){
	 and voters.vote_time=#voteTime#
	@}
	@if(!isEmpty(voteHeight)){
	 and voters.vote_height=#voteHeight#
	@}
	@if(!isEmpty(voteValue)){
	 and voters.vote_value=#voteValue#
	@}
	@if(!isEmpty(totalValue)){
	 and voters.total_value=#totalValue#
	@}

orderBy
===
	order by voters.create_time desc

groupById
===
    group by voters.id


queryNewHeight
===

    select voters.block_height 
    from voters 
    where voters.deleted = 0 
    order by voters.block_height desc
    limit 0 ,1
    
    
queryList
===
       SELECT @pageTag(){
           	b.id,b.create_time,b.update_time,b.deleted,b.weight,b.json,b.version,b.create_user,b.update_user,b.address,b.txid,b.block_height,b.vote_time,b.vote_height,b.vote_value,b.total_value
       @}   
       FROM
       (select 
       	a.id,a.create_time,a.update_time,a.deleted,a.weight,a.json,a.version,a.create_user,a.update_user,a.address,a.txid,a.block_height,a.vote_time,a.vote_height,a.vote_value,a.total_value
       	from
       	(select #use("cols")# 
       	FROM voters 
       	where voters.deleted=0 
       	@if(!isEmpty(address)){
        	 and voters.address=#address#
        @}
       @if(!isEmpty(create)){
       	 and voters.create_time>=#create#
       	@}
       	@if(!isEmpty(end)){
        	 and  #end#>=voters.create_time
        @}
       	ORDER BY voters.block_height desc) a
       	GROUP BY a.address
       ORDER BY a.vote_value desc
       	) b
       	
       	
queryInfoList
===
     SELECT @pageTag(){
               	#use("cols")#
           @} 
     from voters
     where voters.deleted=0 and voters.address=#address# 	
     ORDER BY voters.block_height desc
           