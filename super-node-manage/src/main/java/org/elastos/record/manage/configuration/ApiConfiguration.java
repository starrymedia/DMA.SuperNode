package org.elastos.record.manage.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.elastos.record.utility.dto.NodeInfoDto;
import org.elastos.record.utility.dto.VotersDto;
import org.elastos.record.utility.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/6/13 14:18
 * @Description:
 */

@Configuration
public class ApiConfiguration {

    //wallet api
    private final static String API_V = "/api/1";
    private final static String API_HEIGHT = API_V + "/currHeight";
    private final static String API_RANK = API_V + "/dpos/rank/height/";
    private final static String API_PRODUCER = API_V + "/dpos/producer/";
    private final static String API_ADDRESS = API_V + "/dpos/address/";

    //node api
    private final static String NODE_V = "/api/v1";
    private final static String NODE_BLOCK_BY_HEIGHT = NODE_V + "/block/details/height/";

    private final static String MINING_TXID = "0000000000000000000000000000000000000000000000000000000000000000";
    @Autowired
    private NodeConfiguration nodeConfiguration;

    /**
     * 根据块号获取挖矿交易
     *
     * @param height
     * @return
     */
    public JSONObject getMiningTransactionsByHeight(Long height) {
        try {
            String url = nodeConfiguration.getNodeApiUrl() + NODE_BLOCK_BY_HEIGHT + height;
            String result = getNodeApiResult(url);
            if (result != null) {
                JSONObject resultJson = JSONObject.parseObject(result);
                JSONArray txArray = resultJson.getJSONArray("tx");
                for (int i = 0; i < txArray.size(); i++) {
                    JSONObject tx = txArray.getJSONObject(i);
                    JSONArray vinArray = tx.getJSONArray("vin");
                    for (int j = 0; j < vinArray.size(); j++) {
                        JSONObject vin = vinArray.getJSONObject(j);
                        String txid = vin.getString("txid");
                        if (txid.equals(MINING_TXID)) {
                            return tx;
                        }
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


    /*
     * 获取当前块高度
     * */
    public String getHeight() {
        String url = nodeConfiguration.getWalletApiUrl() + API_HEIGHT;
        return getWalletApiResult(url);
    }

    /**
     * 根据块高度获取dpos节点列表
     *
     * @param height
     * @return
     */
    public List<NodeInfoDto> getNodeListByHeight(Long height) {
        List<NodeInfoDto> list = new ArrayList<>();
        try {
            String url = nodeConfiguration.getWalletApiUrl() + API_RANK + height;
            String result = getWalletApiResult(url);
            if (result != null) {
                JSONArray jsonArray = JSONArray.parseArray(result);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    NodeInfoDto nodeInfoDto = getNodeInfoDto(object);
                    list.add(nodeInfoDto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    /**
     * 获取当前节点列表
     *
     * @return
     */
    public List<NodeInfoDto> getNodeList() {
        String height = getHeight();
        if (height != null) {
            return getNodeListByHeight(Long.valueOf(height));
        }
        return null;

    }

    private NodeInfoDto getNodeInfoDto(JSONObject object) {
        NodeInfoDto nodeInfoDto = new NodeInfoDto();
        String Producer_public_key = object.getString("Producer_public_key");
        String Value = object.getString("Value");
        String Address = object.getString("Address");
        Integer Rank = object.getInteger("Rank");
        String Ownerpublickey = object.getString("Ownerpublickey");
        String Nodepublickey = object.getString("Nodepublickey");
        String Nickname = object.getString("Nickname");
        String Url = object.getString("Url");
        String Location = object.getString("Location");
        Boolean Active = object.getBoolean("Active");
        String Votes = object.getString("Votes");
        String Netaddress = object.getString("Netaddress");
        String State = object.getString("State");
        String Registerheight = object.getString("Registerheight");
        String Cancelheight = object.getString("Cancelheight");
        String Inactiveheight = object.getString("Inactiveheight");
        String Illegalheight = object.getString("Illegalheight");
        String Index = object.getString("Index");
        String Reward = object.getString("Reward");
        String EstRewardPerYear = object.getString("EstRewardPerYear");

        nodeInfoDto.setProducerPublicKey(Producer_public_key);
        nodeInfoDto.setValue(Value);
        nodeInfoDto.setAddress(Address);
        nodeInfoDto.setRank(Rank);
        nodeInfoDto.setOwnerPublicKey(Ownerpublickey);
        nodeInfoDto.setNodePublicKey(Nodepublickey);
        nodeInfoDto.setNickName(Nickname);
        nodeInfoDto.setUrl(Url);
        nodeInfoDto.setLocation(Location);
        nodeInfoDto.setActive(Active);
        nodeInfoDto.setVotes(Votes);
        nodeInfoDto.setNetAddress(Netaddress);
        nodeInfoDto.setState(State);
        nodeInfoDto.setRegisterHeight(Registerheight);
        nodeInfoDto.setCancelHeight(Cancelheight);
        nodeInfoDto.setInactiveHeight(Inactiveheight);
        nodeInfoDto.setIndex(Index);
        nodeInfoDto.setReward(Reward);
        nodeInfoDto.setEstRewardPerYear(EstRewardPerYear);
        nodeInfoDto.setIllegalHeight(Illegalheight);
        return nodeInfoDto;
    }

    /**
     * 根据地址，获取投票节点列表
     *
     * @param address
     * @return
     */
    public List<NodeInfoDto> getNodeListByAddress(String address) {
        List<NodeInfoDto> list = new ArrayList<>();
        try {
            String url = nodeConfiguration.getWalletApiUrl() + API_ADDRESS + address;
            String result = getWalletApiResult(url);
            if (result != null) {
                JSONArray jsonArray = JSONArray.parseArray(result);
                JSONObject Vote = jsonArray.getJSONObject(0);
                JSONArray Vote_Body = Vote.getJSONArray("Vote_Body");
                for (int i = 0; i < Vote_Body.size(); i++) {
                    JSONObject object = Vote_Body.getJSONObject(i);
                    NodeInfoDto nodeInfoDto = getNodeInfoDto(object);
                    list.add(nodeInfoDto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
     * 获取节点投票者
     * */
    public List<VotersDto> getVoters(String producerPublicKey) {
        List<VotersDto> list = new ArrayList<>();
        String url = nodeConfiguration.getWalletApiUrl() + API_PRODUCER + producerPublicKey;
        String result = getWalletApiResult(url);
        if (result != null) {
            JSONArray jsonArray = JSONArray.parseArray(result);
            for (int i = 0; i < jsonArray.size(); i++) {
                VotersDto votersDto = new VotersDto();
                JSONObject object = jsonArray.getJSONObject(i);
                String Producer_public_key = object.getString("Producer_public_key");
                String Vote_type = object.getString("Vote_type");
                String Txid = object.getString("Txid");
                String Value = object.getString("Value");
                String Address = object.getString("Address");
                Long Block_time = object.getLong("Block_time");
                String Height = object.getString("Height");
                Date time = new Date(Block_time * 1000L);
                votersDto.setAddress(Address);
                votersDto.setHeight(Height);
                votersDto.setProducerPublicKey(Producer_public_key);
                votersDto.setTime(time);
                votersDto.setTxid(Txid);
                votersDto.setValue(Value);
                votersDto.setVoteType(Vote_type);
                list.add(votersDto);
            }


        }
        return list;
    }

    private String getWalletApiResult(String url) {

        try {
            String result = HttpUtils.get(url);
            if (!StringUtils.isBlank(result)) {
                JSONObject resultJSONObject = JSON.parseObject(result);
                Integer status = resultJSONObject.getInteger("status");
                if (status == 200) {
                    return resultJSONObject.getString("result");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private String getNodeApiResult(String url) {

        try {
            String result = HttpUtils.get(url);
            if (!StringUtils.isBlank(result)) {
                JSONObject resultJSONObject = JSON.parseObject(result);
                Integer status = resultJSONObject.getInteger("Error");
                if (status == 0) {
                    return resultJSONObject.getString("Result");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
