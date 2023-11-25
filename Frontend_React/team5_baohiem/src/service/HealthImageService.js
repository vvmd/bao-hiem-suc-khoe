import axios from 'axios';

const HEALTH_IMAGE_API_BASE_URL = "http://localhost:8080/health-images";

class HealthImageService {

    getAllHealthImages(){
        return axios.get(HEALTH_IMAGE_API_BASE_URL);
    }

    getHealthImageByID(ID){
        return axios.get(HEALTH_IMAGE_API_BASE_URL + '/' + ID);
    }

    uploadHealthImage(registrator, description, file){
        return axios.post(HEALTH_IMAGE_API_BASE_URL, registrator, description, file);
    }

    getHealthImageByRegistrator(registrator){
        return axios.get(HEALTH_IMAGE_API_BASE_URL + '/find/registrator/' + registrator);
    }
}

export default new HealthImageService()