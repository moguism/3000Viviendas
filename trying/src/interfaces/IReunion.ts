import type ITipoReunion from "./ITipoReunion";

export default interface IReunion {
    id: number,
    tipoReunion: ITipoReunion,
    fecha: Date
}