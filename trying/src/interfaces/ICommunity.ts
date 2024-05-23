import type IBloque from '../interfaces/IBloque.ts' 
export default interface ICommunity {
    id: number,
    nombre: string,
    direccion: string,
    bloques: Array<IBloque>;
}