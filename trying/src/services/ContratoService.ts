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

    async createContrato(contratante_id: number, tipo_contrato_id: number, comunidad_id: number){
        const data:IContrato = {
            id: 0,
            contratante_id: contratante_id,
            tipo_contrato_id: tipo_contrato_id,
            comunidad_id: comunidad_id,
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
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateContrato(id: number, contratante_id: number, tipo_contrato_id: number, comunidad_id: number){
        const uri = `${this.baseUri}/${id}`
        const data:IContrato = {
            id: 0,
            contratante_id: contratante_id,
            tipo_contrato_id: tipo_contrato_id,
            comunidad_id: comunidad_id,
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}