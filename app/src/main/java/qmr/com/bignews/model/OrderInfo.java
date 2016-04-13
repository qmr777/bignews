package qmr.com.bignews.model;

import java.util.List;

/**
 * Created by qmr on 2016/4/13.
 */
public class OrderInfo {

    /**
     * errcode : 0
     * result : success
     * retData : [{"errcode":"0","start_time":"2016-03-16","order_date":"2016-04-12 00:00:00.0","room_price":"99","total_cost":null,"user_name":"qmr777","end_time":"2016-03-21","person_name":"高嘎嘣","room_type":"标准间","person_id":"5555"},{"errcode":"0","start_time":"2016-03-20","order_date":"2016-04-12 09:36:11.0","room_price":"179","total_cost":"179","user_name":"qmr777","end_time":"2016-03-21","person_name":"1","room_type":"双床房","person_id":"1"},{"errcode":"0","start_time":"2016-03-20","order_date":"2016-04-12 09:36:11.0","room_price":"179","total_cost":"179","user_name":"qmr777","end_time":"2016-03-21","person_name":"1","room_type":"双床房","person_id":"1"},{"errcode":"0","start_time":"2016-03-20","order_date":"2016-04-12 09:36:22.0","room_price":"179","total_cost":"179","user_name":"qmr777","end_time":"2016-03-21","person_name":"1","room_type":"双床房","person_id":"1"},{"errcode":"0","start_time":"2016-03-25","order_date":"2016-04-12 09:38:20.0","room_price":"99","total_cost":"1584","user_name":"qmr777","end_time":"2016-04-10","person_name":"齐明睿","room_type":"标准间","person_id":"1"},{"errcode":"0","start_time":"2016-03-14","order_date":"2016-04-12 09:52:17.0","room_price":"199","total_cost":"398","user_name":"qmr777","end_time":"2016-03-16","person_name":"王东","room_type":"大床房","person_id":"19940712"},{"errcode":"0","start_time":"2016-03-20","order_date":"2016-04-12 09:56:45.0","room_price":"259","total_cost":"518","user_name":"qmr777","end_time":"2016-03-22","person_name":"1","room_type":"商务房","person_id":"1"},{"errcode":"0","start_time":"2016-03-13","order_date":"2016-04-12 09:58:07.0","room_price":"179","total_cost":"537","user_name":"qmr777","end_time":"2016-03-16","person_name":"孙旭东","room_type":"双床房","person_id":"3701993"},{"errcode":"0","start_time":"2016-03-14","order_date":"2016-04-12 09:59:57.0","room_price":"179","total_cost":"1611","user_name":"qmr777","end_time":"2016-03-23","person_name":"77","room_type":"双床房","person_id":"444"},{"errcode":"0","start_time":"2016-03-13","order_date":"2016-04-12 10:09:21.0","room_price":"179","total_cost":"537","user_name":"qmr777","end_time":"2016-03-16","person_name":"1q1","room_type":"双床房","person_id":"55886"},{"errcode":"0","start_time":"2016-03-13","order_date":"2016-04-12 22:10:48.0","room_price":"99","total_cost":"297","user_name":"qmr777","end_time":"2016-03-16","person_name":"q1","room_type":"标准间","person_id":"1266"},{"errcode":"0","start_time":"2016-03-13","order_date":"2016-04-12 22:12:31.0","room_price":"259","total_cost":"2590","user_name":"qmr777","end_time":"2016-03-23","person_name":"1","room_type":"商务房","person_id":"1"},{"errcode":"0","start_time":"2016-03-20","order_date":"2016-04-12 22:13:58.0","room_price":"99","total_cost":"297","user_name":"qmr777","end_time":"2016-03-23","person_name":"1","room_type":"标准间","person_id":"0"},{"errcode":"0","start_time":"2016-03-20","order_date":"2016-04-12 22:14:41.0","room_price":"179","total_cost":"358","user_name":"qmr777","end_time":"2016-03-22","person_name":"1","room_type":"双床房","person_id":"1"},{"errcode":"0","start_time":"2016-04-13","order_date":"2016-04-12 22:17:20.0","room_price":"179","total_cost":"358","user_name":"qmr777","end_time":"2016-04-15","person_name":"孙旭东","room_type":"双床房","person_id":"123"},{"errcode":"0","start_time":"2016-04-13","order_date":"2016-04-12 22:20:06.0","room_price":"99","total_cost":"297","user_name":"qmr777","end_time":"2016-04-16","person_name":"1","room_type":"标准间","person_id":"1"}]
     */

    private String errcode;
    private String result;
    /**
     * errcode : 0
     * start_time : 2016-03-16
     * order_date : 2016-04-12 00:00:00.0
     * room_price : 99
     * total_cost : 15
     * user_name : qmr777
     * end_time : 2016-03-21
     * person_name : 高嘎嘣
     * room_type : 标准间
     * person_id : 5555
     */

    private List<RetDataBean> retData;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<RetDataBean> getRetData() {
        return retData;
    }

    public void setRetData(List<RetDataBean> retData) {
        this.retData = retData;
    }

    public static class RetDataBean {
        private String errcode;
        private String start_time;
        private String order_date;
        private String room_price;
        private String total_cost;
        private String user_name;
        private String end_time;
        private String person_name;
        private String room_type;
        private String person_id;

        public String getErrcode() {
            return errcode;
        }

        public void setErrcode(String errcode) {
            this.errcode = errcode;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getRoom_price() {
            return room_price;
        }

        public void setRoom_price(String room_price) {
            this.room_price = room_price;
        }

        public String getTotal_cost() {
            return total_cost;
        }

        public void setTotal_cost(String total_cost) {
            this.total_cost = total_cost;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getRoom_type() {
            return room_type;
        }

        public void setRoom_type(String room_type) {
            this.room_type = room_type;
        }

        public String getPerson_id() {
            return person_id;
        }

        public void setPerson_id(String person_id) {
            this.person_id = person_id;
        }
    }
}
