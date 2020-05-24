
import { Entity, Column, PrimaryGeneratedColumn, ManyToOne, ManyToMany, JoinTable } from 'typeorm';
import { User } from './user.entity';

@Entity()
export class ChatRoom {
  @PrimaryGeneratedColumn()
  id: number;

  @ManyToOne(type => User, user => user.rooms)
  owner: User;

  @Column()
  name: string;

  @ManyToMany(type => User)
  @JoinTable()
  joinedUsers: User[];
}
