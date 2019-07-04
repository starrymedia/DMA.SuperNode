
all
===
    select share.#use("cols")# from share where share.deleted = 0

sample
===
	select #use("cols")# from share share where  #use("condition")# #use("orderBy")#

cols
===
	share.id,share.address,share.vote_value,share.vote_total_value,share.balance,share.block_height,share.is_send,share.create_time,share.update_time,share.deleted,share.weight,share.version,share.json
updateSample
===
	share.id=#id#,share.address=#address#,share.vote_value=#voteValue#,share.vote_total_value=#voteTotalValue#,share.balance=#balance#,share.block_height=#blockHeight#,share.is_send=#isSend#,share.create_time=#createTime#,share.update_time=#updateTime#,share.deleted=#deleted#,share.weight=#weight#,share.version=#version#,share.json=#json#
forceDeleteByIds
===
    delete from share where share.id in (#ids#)

deleteByIds
===
    update share set share.deleted = 1 where share.id in (#{ids})

condition
===
    share.deleted = 0
	@if(!isEmpty(id)){
	 and share.id=#id#
	@}
	@if(!isEmpty(address)){
	 and share.address=#address#
	@}
	@if(!isEmpty(voteValue)){
	 and share.vote_value=#voteValue#
	@}
	@if(!isEmpty(voteTotalValue)){
	 and share.vote_total_value=#voteTotalValue#
	@}
	@if(!isEmpty(balance)){
	 and share.balance=#balance#
	@}
	@if(!isEmpty(blockHeight)){
	 and share.block_height=#blockHeight#
	@}
	@if(!isEmpty(isSend)){
	 and share.is_send=#isSend#
	@}
	@if(!isEmpty(createTime)){
	 and share.create_time=#createTime#
	@}
	@if(!isEmpty(updateTime)){
	 and share.update_time=#updateTime#
	@}
	@if(!isEmpty(deleted)){
	 and share.deleted=#deleted#
	@}
	@if(!isEmpty(weight)){
	 and share.weight=#weight#
	@}
	@if(!isEmpty(version)){
	 and share.version=#version#
	@}
	@if(!isEmpty(json)){
	 and share.json=#json#
	@}

orderBy
===
	order by share.create_time desc

groupById
===
    group by share.id


querySumBalance
===
    select 
    share.address,share.is_send,share.deleted,sum(share.balance) as balance
    from share
    where share.deleted=0
    @if(!isEmpty(isSend)){
    	 and share.is_send=#isSend#
    @}
	@if(!isEmpty(startHeight)){
	 and share.block_height>=#startHeight#
	@}
	@if(!isEmpty(endHeight)){
	 and  #endHeight# >=share.block_height
	@}
    @if(!isEmpty(address)){
	 and share.address=#address#
	@}
	group by share.address

updateSendStatusByBlockHeight
===
     update share 
     set share.is_send=#isSend# 
     where share.deleted=0
     @if(!isEmpty(startHeight)){
     	and share.block_height>=#startHeight#
     @}
     @if(!isEmpty(endHeight)){
     	and  #endHeight# >=share.block_height
     @}
     @if(!isEmpty(address)){
        and share.address=#address#
      @}
	