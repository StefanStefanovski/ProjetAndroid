
import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Actuality {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({nullable: true})
  title: string;

  @Column()
  description: string;

  @Column()
  category: string;
}
