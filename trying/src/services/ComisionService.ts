import type IComision from "@/interfaces/IComision";

export default class ComisionService {

    baseUrl: string = "http://127.0.0.1:8080/api/comisiones";

    async listAllComisions(): Promise<Array<IComision>> {
        const rawResponse = await fetch(this.baseUrl)
        const response = await rawResponse.json()
        return response
    }

    async listComisionById(id: number): Promise<IComision> {
        const uri = `${this.baseUrl}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createComision(creado_en: Date, actualizado_en: Date){
        const data:IComision = {
            id: 0,
            created_at: creado_en,
            updated_at: actualizado_en,
        }
        const rawResponse = await fetch(this.baseUrl, {
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

    async deleteComision(id: number){
        const uri = `${this.baseUrl}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateComision(id: number, creado_en: Date, actualizado_en: Date){
        const uri = `${this.baseUrl}/${id}`
        const data:IComision = {
            id: 0,
            created_at: creado_en,
            updated_at: actualizado_en,
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