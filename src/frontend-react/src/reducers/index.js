// Redux는 하나의 store만 가지므로 reducer 결합
import { combineReducers } from "redux";
import auth from "./auth";
import message from "./message";

export default combineReducers({
    auth,
    message,
});