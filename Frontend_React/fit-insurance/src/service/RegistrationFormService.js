import axios from 'axios';

const REGISTRATION_FORM_API_BASE_URL = "http://localhost:8080/registrations";

class RegistrationFormService {

    getAllForms(){
        return axios.get(REGISTRATION_FORM_API_BASE_URL);
    }

    getFormByID(formID){
        return axios.get(REGISTRATION_FORM_API_BASE_URL + '/' + formID);
    }

    createForm(name, birthday, IDCard, phone, address, insuranceID, registrator){
        return axios.post(REGISTRATION_FORM_API_BASE_URL, name, birthday, IDCard, phone, address, insuranceID, registrator);
    }

    updateForm(formID, name, birthday, IDCard, phone, address, insuranceID, status, approvedBy){
        return axios.put(REGISTRATION_FORM_API_BASE_URL, formID, name, birthday, IDCard, phone, address, insuranceID, status, approvedBy);
    }

    updateHealth(formID, healthImageID){
        return axios.put(REGISTRATION_FORM_API_BASE_URL + '/update-health', formID, healthImageID);
    }

    deleteForm(formID){
        return axios.delete(REGISTRATION_FORM_API_BASE_URL + '/' + formID);
    }

    getFormByIDCard(IDCard){
        return axios.get(REGISTRATION_FORM_API_BASE_URL + '/find/IDCard/' + IDCard);
    }

    getFormByRegistrator(registrator){
        return axios.get(REGISTRATION_FORM_API_BASE_URL + '/find/registrator/' + registrator);
    }

    getFormByStatus(status){
        return axios.get(REGISTRATION_FORM_API_BASE_URL + '/find/status/' + status);
    }

    getFormByCreatedDateAfter(date){
        return axios.get(REGISTRATION_FORM_API_BASE_URL + '/find/date-after/' + date);
    }
}

export default new RegistrationFormService()