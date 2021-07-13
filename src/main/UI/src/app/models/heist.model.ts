import {Requirement} from './requirement.model';
import {Member} from './member.model';

export class Heist{
  name: string;
  location: string;
  startTime: Date;
  endTime: Date;
  skills?: Requirement[];
  members?: Member[];
  status?: string;
  outcome?: string;
}
