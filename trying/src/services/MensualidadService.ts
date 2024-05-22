import type IMensualidad from "@/interfaces/IMensualidad";

export default class MensualidadService {

    baseUri: string = "http://127.0.0.1:8080/api/mensualidades"

    async listAllMensualidades(): Promise<Array<IMensualidad>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listMensualidadById(id: number): Promise<IMensualidad> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createMensualidad(fecha: Date, cuantia: number){
        const data:IMensualidad = {
            id: 0,
            fecha: fecha,
            cuantia: cuantia
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

    async deleteMensualidad(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateMensualidad(id: number, fecha: Date, cuantia: number){
        const data:IMensualidad = {
            id: id,
            fecha: fecha,
            cuantia: cuantia
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