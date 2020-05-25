
import { Entity, Column, PrimaryGeneratedColumn, ManyToOne, CreateDateColumn } from 'typeorm';
import { User } from './user.entity';
import { ChatRoom } from './chatroom';

@Entity()
export class ChatMessage {
  @PrimaryGeneratedColumn()
  id: number;

  @ManyToOne(type => ChatRoom, room => room.messages)
  room: ChatRoom;

  @Column()
  username: string;

  @Column()
  message: string;

  @CreateDateColumn()
  date: Date;
}
