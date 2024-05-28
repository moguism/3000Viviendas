import type IRol from "@/interfaces/IRol";
import type IVecino from "@/interfaces/IVecino";
import type IVivienda from "@/interfaces/IVivienda";

export default class VecinoService {

    baseUri: string = 'http://127.0.0.1:8080/api/vecinos'

    async listAllVecinos(): Promise<Array<IVecino>> {
        const response = await fetch(this.baseUri)
        const vecinos = await response.json()
        return vecinos
    }

    async listVecinoById(id: number): Promise<IVecino> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createVecino(name: string, viviendas: Array<IVivienda>, rol: IRol) {
        const data:IVecino = {
            id: 0,
            nombre: name,
            viviendas: viviendas,
            rol: rol,
            nombreRol: rol.nombre
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteVecino(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateVecino(id: number, name: string, viviendas: Array<IVivienda>, rol: IRol) {
        const data:IVecino = {
            id: id,
            nombre: name,
            viviendas: viviendas,
            rol: rol,
            nombreRol: rol.nombre
        }
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}