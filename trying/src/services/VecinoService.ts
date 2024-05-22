import type IVecino from "@/interfaces/IVecino";

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

    async createVecino(name: string, vivienda_id: number) {
        const data:IVecino = {
            id: 0,
            nombre: name,
            vivienda_id: vivienda_id
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteVecino(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateVecino(id: number, name: string, vivienda_id: number) {
        const data:IVecino = {
            id: id,
            nombre: name,
            vivienda_id: vivienda_id
        }
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}