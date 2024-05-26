import type ICommunity from "./ICommunity";
import type IContratante from "./IContratante";

export default interface IContrato {
    id: number,
    contratante: IContratante,
    comunidad: ICommunity,
    created_at: Date
}