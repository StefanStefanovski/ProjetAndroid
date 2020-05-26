
import { Entity, Column, PrimaryGeneratedColumn, OneToMany, ManyToMany, JoinTable } from 'typeorm';
import { ChatRoom } from './chatroom';

@Entity()
export class User {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  name: string;

  @Column()
  email: string;

  @Column()
  city: string;

  @Column({ default: true })
  isActive: boolean;

  @Column()
  password: string;


  @ManyToMany(type => ChatRoom, inverse => inverse.requestedjoin)
  requestedjoinChatRoom: ChatRoom[];

  @ManyToMany(type => ChatRoom, inverse => inverse.joinedChatRoom)
  joinedChatRoom: ChatRoom[];

  @OneToMany(type => ChatRoom, chatroom => chatroom.owner)
  rooms: ChatRoom[];
}
