import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:9999/api/",
  headers: {
    "Content-Type": "application/json",
    HEADER_AUTH: sessionStorage.getItem("access-token"),
  },
});

export default instance;
