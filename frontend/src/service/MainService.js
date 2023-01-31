import axios from "axios";

const PATH = "http://localhost:8080/math/";

class MainService {
    static getData() {
        return axios.get(PATH, {
            params: {
                count: 5
            }
        });
    }
}

export default MainService;
