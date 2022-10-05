// 어플리케이션의 어느 곳에서 디스패치가 일어나면 '메시지 state'업데이트
import { SET_MESSAGE, CLEAR_MESSAGE } from "../actions/types";

const initialState = {};

export default function (state = initialState, action) {
    const { type, payload } = action;

    switch (type) {
        case SET_MESSAGE:
            return { message: payload };
        case CLEAR_MESSAGE:
            return { message: "" };
        default:
            return state;
    }
}