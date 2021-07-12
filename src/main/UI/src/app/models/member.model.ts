import {Skill} from './skill.model';

export class Member{
  name: string;
  sex: string;
  email: string;
  skills: Skill[];
  mainSkill: string;
  status: string;
}
