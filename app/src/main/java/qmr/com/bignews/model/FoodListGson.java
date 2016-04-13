package qmr.com.bignews.model;

import java.util.List;

/**
 * Created by qmr on 2016/3/30.
 */
public class FoodListGson {


    /**
     * result : 查询成功
     * errcode : 0
     * retData : [{"food_name":"炒方便面","food_price":"5","food_id":"1","food_image":"http://10.0.2.2:8080/160328/images/food1.jpg"},{"food_name":"牛奶鸡蛋布丁","food_price":"6","food_id":"2","food_image":"http://10.0.2.2:8080/160328/images/food2.jpg"},{"food_name":"冰激凌","food_price":"7","food_id":"3","food_image":"http://10.0.2.2:8080/160328/images/food3.jpg"},{"food_name":"葱绿拌葱白","food_price":"5","food_id":"4","food_image":"http://10.0.2.2:8080/160328/images/food4.jpg"},{"food_name":"鸡翅","food_price":"10","food_id":"5","food_image":"http://10.0.2.2:8080/160328/images/food5.jpg"},{"food_name":"芒果糯米","food_price":"6","food_id":"6","food_image":"http://10.0.2.2:8080/160328/images/food6.jpg"},{"food_name":"小笼包","food_price":"10","food_id":"7","food_image":"http://10.0.2.2:8080/160328/images/food7.jpg"},{"food_name":"大盘鸡翅","food_price":"15","food_id":"8","food_image":"http://10.0.2.2:8080/160328/images/food8.jpg"},{"food_name":"2.5L大桶plus可口可乐","food_price":"5","food_id":"9","food_image":"http://10.0.2.2:8080/160328/images/food9.jpg"}]
     */

    private String result;
    private String errcode;
    /**
     * food_name : 炒方便面
     * food_price : 5
     * food_id : 1
     * food_image : http://10.0.2.2:8080/160328/images/food1.jpg
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
        private String food_name;
        private String food_price;
        private String food_id;
        private String food_image;

        public String getFood_name() {
            return food_name;
        }

        public void setFood_name(String food_name) {
            this.food_name = food_name;
        }

        public String getFood_price() {
            return food_price;
        }

        public void setFood_price(String food_price) {
            this.food_price = food_price;
        }

        public String getFood_id() {
            return food_id;
        }

        public void setFood_id(String food_id) {
            this.food_id = food_id;
        }

        public String getFood_image() {
            return food_image;
        }

        public void setFood_image(String food_image) {
            this.food_image = food_image;
        }
    }
}
