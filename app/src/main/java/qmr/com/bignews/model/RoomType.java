package qmr.com.bignews.model;

import java.util.List;

/**
 * Created by qmr on 2016/4/10.
 */
public class RoomType {

    /**
     * result : 查询成功
     * errcode : 0
     * retData : [{"roomPrice":"199","roomType":"大床房"},{"roomPrice":"99","roomType":"标准间"},{"roomPrice":"179","roomType":"双床房"},{"roomPrice":"259","roomType":"商务房"},{"roomPrice":"99","roomType":"可乐管够房"}]
     */

    private String result;
    private String errcode;
    /**
     * roomPrice : 199
     * roomType : 大床房
     */

    private List<RetDataBean> retData;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public List<RetDataBean> getRetData() {
        return retData;
    }

    public void setRetData(List<RetDataBean> retData) {
        this.retData = retData;
    }

    public static class RetDataBean {
        private String roomPrice;
        private String roomType;

        public String getRoomPrice() {
            return roomPrice;
        }

        public void setRoomPrice(String roomPrice) {
            this.roomPrice = roomPrice;
        }

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }
    }
}
