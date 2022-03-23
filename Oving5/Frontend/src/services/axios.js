import axios from "axios";
const config = {
  headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
};

export function postEquation(equation){
  return axios.post("http://localhost:8080/calc/solve", equation, config).then((response) => {
    console.log(response.data)
    return response.data
  })
}

export function getAnswer(){
  return axios.get("http://localhost:8080/calc/ans", config).then((response) => {
    return response.data
  })
}

export function getLog(page){
  return axios.get(`http://localhost:8080/calc/log/${page}`, config).then((response) => {
    return response.data
  })
}
