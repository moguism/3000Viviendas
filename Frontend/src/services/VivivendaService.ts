import type IVivienda from "@/interfaces/IVivienda";

export default class ViviendaService {

    baseUri: string = "http://127.0.0.1:8080/api/viviendas"

    async listAllViviendas(): Promise<Array<IVivienda>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listViviendaById(id: number): Promise<IVivienda> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createVivienda(bloque_id: number, escalera: string, planta: string, puerta: string, letra: string){
        const data:IVivienda = {
            id: 0,
            bloque_id: bloque_id,
            escalera: escalera,
            planta: planta,
            puerta: puerta,
            letra: letra
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteVivienda(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateVivienda(id: number, bloque_id: number, escalera: string, planta: string, puerta: string, letra: string){
        const data:IVivienda = {
            id: id,
            bloque_id: bloque_id,
            escalera: escalera,
            planta: planta,
            puerta: puerta,
            letra: letra
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