
all
===
    select node_income.#use("cols")# from node_income where node_income.deleted = 0

sample
===
	select #use("cols")# from node_income node_income where  #use("condition")# #use("orderBy")#

cols
===
	node_income.id,node_income.block_height,node_income.income_value,node_income.management_value,node_income.team_value,node_income.community_value,node_income.create_time,node_income.update_time,node_income.deleted,node_income.weight,node_income.json,node_income.version
updateSample
===
	node_income.id=#id#,node_income.block_height=#blockHeight#,node_income.income_value=#incomeValue#,node_income.management_value=#managementValue#,node_income.team_value=#teamValue#,node_income.community_value=#communityValue#,node_income.create_time=#createTime#,node_income.update_time=#updateTime#,node_income.deleted=#deleted#,node_income.weight=#weight#,node_income.json=#json#,node_income.version=#version#
forceDeleteByIds
===
    delete from node_income where node_income.id in (#ids#)

deleteByIds
===
    update node_income set node_income.deleted = 1 where node_income.id in (#{ids})

condition
===
    node_income.deleted = 0
	@if(!isEmpty(id)){
	 and node_income.id=#id#
	@}
	@if(!isEmpty(blockHeight)){
	 and node_income.block_height=#blockHeight#
	@}
	@if(!isEmpty(incomeValue)){
	 and node_income.income_value=#incomeValue#
	@}
	@if(!isEmpty(managementValue)){
	 and node_income.management_value=#managementValue#
	@}
	@if(!isEmpty(teamValue)){
	 and node_income.team_value=#teamValue#
	@}
	@if(!isEmpty(communityValue)){
	 and node_income.community_value=#communityValue#
	@}
	@if(!isEmpty(createTime)){
	 and node_income.create_time=#createTime#
	@}
	@if(!isEmpty(updateTime)){
	 and node_income.update_time=#updateTime#
	@}
	@if(!isEmpty(deleted)){
	 and node_income.deleted=#deleted#
	@}
	@if(!isEmpty(weight)){
	 and node_income.weight=#weight#
	@}
	@if(!isEmpty(json)){
	 and node_income.json=#json#
	@}
	@if(!isEmpty(version)){
	 and node_income.version=#version#
	@}

orderBy
===
	order by node_income.create_time desc

groupById
===
    group by node_income.id

queryNewHeight
===
    select node_income.block_height 
    from node_income 
    where node_income.deleted = 0 
    order by node_income.block_height desc
    limit 0 ,1
    
    
querySumByHeight
===
    select sum(node_income.income_value) as income_value,sum(node_income.management_value) as management_value,
    sum(node_income.team_value) as team_value,sum(node_income.community_value) as community_value
    from node_income
    where node_income.deleted = 0 
    @if(!isEmpty(startHeight)){
    	 and node_income.block_height > #startHeight#
    @}
    @if(!isEmpty(endHeight)){
       and  #endHeight# >= node_income.block_height
    @}
    
    