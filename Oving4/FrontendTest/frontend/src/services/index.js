import axios from "axios";

export function postEquation(equation){
  return axios.post("http://localhost:8080/calc/solve", equation).then((response) => {
    console.log(response.data)
    return response.data
  })
}

export function getAnswer(){
  return axios.get("http://localhost:8080/calc/ans").then((response) => {
    return response.data
  })
}

export function getLog(){
  return axios.get("http://localhost:8080/calc/log").then((response) => {
    return response.data
  })
}
