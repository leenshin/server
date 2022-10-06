import { createStore, applyMiddleware } from "redux";
// import { applyMiddleware } from "redux";
// import { configureStore } from "@reduxjs/toolkit"
import { composeWithDevTools } from "redux-devtools-extension";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const middleware = [thunk];

// const store = configureStore(
//     rootReducer,
//     composeWithDevTools(applyMiddleware(...middleware))
// );

const store = createStore(
    rootReducer,
    composeWithDevTools(applyMiddleware(...middleware))
);

export default store;