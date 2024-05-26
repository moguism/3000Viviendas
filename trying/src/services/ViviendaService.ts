import type IBloque from "@/interfaces/IBloque";
import type IVecino from "@/interfaces/IVecino";
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

    async createVivienda(bloque: IBloque, escalera: string, planta: string, puerta: string, letra: string, vecino: IVecino){
        const data:IVivienda = {
            id: 0,
            bloque: bloque,
            escalera: escalera,
            planta: planta,
            puerta: puerta,
            letra: letra,
            vecino: vecino,
            nombreVecino: vecino.nombre
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

    async deleteVivienda(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateVivienda(id: number, bloque: IBloque, escalera: string, planta: string, puerta: string, letra: string, vecino: IVecino){
        const data:IVivienda = {
            id: id,
            bloque: bloque,
            escalera: escalera,
            planta: planta,
            puerta: puerta,
            letra: letra,
            vecino: vecino,
            nombreVecino: vecino.nombre
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