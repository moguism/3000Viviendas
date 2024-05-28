import type IReunion from "./IReunion"
 
export default interface ITipoReunion {
    id: number,
    nombre: string
    reuniones: Array<IReunion>
}