import type ICommunity from "@/interfaces/ICommunity";
import type IContratante from "@/interfaces/IContratante";
import type IContrato from "@/interfaces/IContrato";

export default class ContratoService {

    baseUri: string = "http://127.0.0.1:8080/api/contratos"

    async listAllContratos(): Promise<Array<IContrato>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listContratoById(id: number): Promise<IContrato> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createContrato(contratante: IContratante, comunidad: ICommunity, created_at: Date){
        const data:IContrato = {
            id: 0,
            contratante: contratante,
            comunidad: comunidad,
            created_at: created_at
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

    async deleteContrato(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateContrato(id: number, contratante: IContratante, comunidad: ICommunity, created_at: Date){
        const uri = `${this.baseUri}/${id}`
        const data:IContrato = {
            id: 0,
            contratante: contratante,
            comunidad: comunidad,
            created_at: created_at
        }
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