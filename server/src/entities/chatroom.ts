
import { Entity, Column, PrimaryGeneratedColumn, ManyToOne, ManyToMany, JoinTable, OneToMany } from 'typeorm';
import { User } from './user.entity';
import { ChatMessage } from './chatmessage';

@Entity()
export class ChatRoom {
  @PrimaryGeneratedColumn()
  id: number;

  @ManyToOne(type => User, user => user.rooms)
  owner: User;

  @Column()
  name: string;

  @Column({default: true,})
  public: boolean;


  @OneToMany(type => ChatMessage, message => message.room)
  messages: ChatMessage[];

  @ManyToMany(type => User)
  @JoinTable()
  requestedjoin: User[];

  @ManyToMany(type => User)
  @JoinTable()
  joined: User[];
}
